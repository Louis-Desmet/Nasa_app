package com.example.affirmations.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.affirmations.ui.APODViewModel
import com.example.affirmations.ui.MarsUiState

@Composable
fun MarsPictureScreen (
marsUiState: MarsUiState, modifier: Modifier = Modifier
) {
when (marsUiState) {
    is MarsUiState.Loading -> {
        //laden
    }
    is MarsUiState.Success -> {
        Text(text = marsUiState.photos)
    }
    is  MarsUiState.Error -> {
        //error
    }
}
}

class ResultScreen(photos: Any, modifier: Modifier) {

}
