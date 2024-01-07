package com.example.affirmations.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.affirmations.NasaApplication
import com.example.affirmations.data.MarsPhotoRepository
import com.example.affirmations.model.MarsPhoto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

/**
 * Sealed interface representing different UI states for Mars photos.
 */
sealed interface MarsUiState {
    object Success : MarsUiState
    object Error : MarsUiState
    object Loading : MarsUiState
}

/**
 * ViewModel for managing Mars photos UI state.
 *
 * @property marsPhotoRepository Repository for fetching Mars photos.
 */
class MarsViewModel(private val marsPhotoRepository: MarsPhotoRepository) : ViewModel() {

    /**
     * The current UI state of the Mars photos screen.
     * Can be [MarsUiState.Success], [MarsUiState.Error], or [MarsUiState.Loading].
     */
    var marsUiState: MarsUiState by mutableStateOf(MarsUiState.Loading)
        private set

    /**
     * StateFlow for managing the list of Mars photos.
     */
    val uiListState: StateFlow<List<MarsPhoto>> = marsPhotoRepository.getMarsPhotos()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList()
        )

    init {
        getMarsImages()
    }

    /**
     * Fetches the latest Mars images and updates the UI state accordingly.
     */
    fun getMarsImages() {
        viewModelScope.launch {
            marsUiState = MarsUiState.Loading
            try {
                marsPhotoRepository.refresh()
                marsUiState = MarsUiState.Success
            } catch (e: IOException) {
                marsUiState = MarsUiState.Error
            } catch (e: HttpException) {
                marsUiState = MarsUiState.Error
            }
        }
    }

    companion object {
        /**
         * Factory for creating instances of [MarsViewModel].
         */
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as NasaApplication)
                val marsPhotoRepository = application.container.marsPhotoRepository
                MarsViewModel(marsPhotoRepository = marsPhotoRepository)
            }
        }
    }
}
