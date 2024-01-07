package com.example.affirmations.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.affirmations.R
import com.example.affirmations.model.APOD
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.affirmations.ui.APODViewModel
import com.example.affirmations.ui.APODuiState
import com.example.affirmations.ui.MarsViewModel

/**
 * Composable function to display a simple "Hello World" screen.
 */
@Composable
fun HelloWorldScreen() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Text(text = "Hello World")
    }
}

/**
 * Composable function to display the APOD (Astronomy Picture of the Day) screen.
 * It switches between different UI states like loading, success, and error.
 *
 * @param apodUiState The current state of the APOD UI.
 * @param retryAction Action to perform on retry.
 * @param modifier Modifier for this composable.
 */
@Composable
fun APODScreen(
    apodUiState: APODuiState, retryAction: () -> Unit, modifier: Modifier = Modifier
) {
    when (apodUiState) {
        is APODuiState.Loading -> {
            LoadingScreen(modifier = modifier.fillMaxSize())
        }

        is APODuiState.Error -> {
            ErrorScreen(retryAction, modifier = modifier.fillMaxSize())
        }

        is APODuiState.Success -> {
            val apod = apodUiState.apod
            APODCard(apod)
        }
    }
}

/**
 * Composable function to display a card with details of an APOD item.
 *
 * @param apod The APOD item to display.
 * @param modifier Modifier for this composable.
 */
@Composable
fun APODCard(apod: APOD, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        apod.url?.let {
            Image(
                painter = rememberAsyncImagePainter(model = it),
                contentDescription = apod.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f),
                contentScale = ContentScale.Crop
            )
        }
        apod.title?.let {
            Text(text = it, style = MaterialTheme.typography.headlineLarge)
        }
        apod.explanation?.let {
            Text(text = it, style = MaterialTheme.typography.bodyMedium)
        }
        // Continue for other fields...
    }
}
