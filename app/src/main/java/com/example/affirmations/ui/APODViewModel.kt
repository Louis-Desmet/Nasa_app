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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface APODuiState {
    data class Success(val apod: APOD) : APODuiState
    object Error : APODuiState
    object Loading : APODuiState
}

class APODViewModel(private val apodRepository: APODRepository) : ViewModel() {

    //private val _uiState = MutableStateFlow<APODuiState>(APODuiState.Loading)

    var apodUiState: APODuiState by mutableStateOf(APODuiState.Loading)
    private set

    init {
        getAPOD()
    }

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
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as NasaApplication)
                val repo = application.container.apodRepository
                APODViewModel(repo)
            }
        }
    }
}


