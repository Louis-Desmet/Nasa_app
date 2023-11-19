package com.example.affirmations.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.affirmations.data.Datasource
import com.example.affirmations.data.NetworkMarsPhotosRepository
import com.example.affirmations.network.MarsApiService
import com.example.affirmations.network.NasaApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface MarsUiState {
    data class Success(val photos: String) : MarsUiState
    object Error : MarsUiState
    object Loading : MarsUiState
}

class APODViewModel : ViewModel() {
    // Initialize your data here
    private val _uiState = MutableStateFlow(APODState())
    val uiState: StateFlow<APODState> = _uiState.asStateFlow()
    //State for the web service

    var marsUiState: MarsUiState by mutableStateOf(MarsUiState.Loading)
        private set



    init {
        loadAPODs()
        getMarsImages()
    }

    //Function to load the apods from the mockData
    private fun loadAPODs() {
        viewModelScope.launch {
            // Emit loading state
            _uiState.value = APODState(isLoading = true)

                val apodsList = Datasource().loadAPODS()
                _uiState.value = APODState(apods = apodsList, isLoading = false)
        }
    }

    //Function to get the mars images (with API call)
    private fun getMarsImages() {
        viewModelScope.launch {
            marsUiState = MarsUiState.Loading
            marsUiState = try {
                val marsPhotosRepository = NetworkMarsPhotosRepository()
                val listResult = marsPhotosRepository.getMarsPhotos()
                MarsUiState.Success(
                    "Success: ${listResult.size} Mars photos retrieved"
                )
            } catch (e: IOException) {
                MarsUiState.Error
            } catch (e: HttpException) {
                MarsUiState.Error
            }
        }

    }


}
