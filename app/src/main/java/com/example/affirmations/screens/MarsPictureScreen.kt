package com.example.affirmations.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.affirmations.ui.APODViewModel
import com.example.affirmations.ui.MarsUiState
import androidx.compose.material3.Text

@Composable
fun MarsPictureScreen (
marsUiState: MarsUiState, modifier: Modifier = Modifier
) {
when (marsUiState) {
    is MarsUiState.Loading -> {
        //laden
    }
    is MarsUiState.Success -> {
    ResultScreen(marsUiState.photos, modifier.fillMaxWidth())

    }
    is  MarsUiState.Error -> {
        //error
    }
}
}
@Composable
fun ResultScreen(photos: String, modifier: Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(text = photos)
    }
}
