package com.example.affirmations.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.affirmations.ui.APODViewModel
import com.example.affirmations.ui.APODuiState

@Composable
fun MarsPictureScreen (
apodUiState: APODuiState, modifier: Modifier = Modifier
) {
when (apodUiState) {
    is APODuiState.Loading -> {
        //laden
    }
    is APODuiState.Success -> {
        Text(text = apodUiState.photos)
    }
    is  APODuiState.Error -> {
        //error
    }
}
}

class ResultScreen(photos: Any, modifier: Modifier) {

}
