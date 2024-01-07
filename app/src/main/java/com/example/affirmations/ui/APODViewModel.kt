package com.example.affirmations.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.affirmations.NasaApplication
import com.example.affirmations.data.APODRepository
import com.example.affirmations.model.APOD
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

/**
 * Sealed interface representing the different states of the APOD UI.
 */
sealed interface APODuiState {
    data class Success(val apod: APOD) : APODuiState
    object Error : APODuiState
    object Loading : APODuiState
}

/**
 * ViewModel for managing the APOD (Astronomy Picture of the Day) UI state.
 *
 * @property apodRepository Repository for fetching APOD data.
 */
class APODViewModel(private val apodRepository: APODRepository) : ViewModel() {

    /**
     * The current UI state of the APOD screen.
     * Can be [APODuiState.Success], [APODuiState.Error], or [APODuiState.Loading].
     */
    var apodUiState: APODuiState by mutableStateOf(APODuiState.Loading)
        private set

    init {
        getAPOD()
    }

    /**
     * Fetches the Astronomy Picture of the Day and updates the UI state accordingly.
     */
    fun getAPOD() {
        viewModelScope.launch {
            apodUiState = APODuiState.Loading
            try {
                val apod = apodRepository.getAPOD()
                apodUiState = APODuiState.Success(apod)
            } catch (e: IOException) {
                apodUiState = APODuiState.Error
            } catch (e: HttpException) {
                apodUiState = APODuiState.Error
            }
        }
    }

    companion object {
        /**
         * Factory for creating instances of [APODViewModel].
         */
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as NasaApplication)
                val repo = application.container.apodRepository
                APODViewModel(repo)
            }
        }
    }
}
