package com.example.affirmations.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.affirmations.data.Datasource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class APODViewModel : ViewModel() {
    // Initialize your data here
    private val _uiState = MutableStateFlow(APODState())
    val uiState: StateFlow<APODState> = _uiState.asStateFlow()

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
}
