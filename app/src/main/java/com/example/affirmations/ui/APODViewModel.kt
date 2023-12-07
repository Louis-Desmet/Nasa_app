package com.example.affirmations.ui

import com.example.affirmations.model.MarsPhoto

sealed interface APODuiState {
    object Success : MarsUiState
    object Error : MarsUiState
    object Loading : MarsUiState
}