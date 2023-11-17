package com.example.affirmations.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.affirmations.data.Datasource
import com.example.affirmations.network.APODApi
import com.example.affirmations.network.APODApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class APODViewModel : ViewModel() {
    // Initialize your data here
    private val _uiState = MutableStateFlow(APODState())
    val uiState: StateFlow<APODState> = _uiState.asStateFlow()
    //State for the web service
    var APODuiState: String by mutableStateOf("")
        private set

    init {
        loadAPODs()
    }

    private fun loadAPODs() {
        viewModelScope.launch {
            // Emit loading state
            _uiState.value = APODState(isLoading = true)

                val apodsList = Datasource().loadAPODS()
                _uiState.value = APODState(apods = apodsList, isLoading = false)
        }
    }

    private fun getAPODImages() {
        viewModelScope.launch {
            val listResult = APODApi.retrofitService.getPhotos()
            APODuiState = listResult
        }

    }
}
