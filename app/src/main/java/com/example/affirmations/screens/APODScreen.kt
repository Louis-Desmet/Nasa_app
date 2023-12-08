package com.example.affirmations.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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

@Composable
fun HelloWorldScreen() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Text(text = "Hello World")
    }
}

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

@Composable
fun APODCard(apod: APOD, modifier: Modifier = Modifier) {

        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Image
            Image(
                painter = rememberAsyncImagePainter(model = apod.url),
                contentDescription = apod.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f),
                contentScale = ContentScale.Crop
            )
            // Title
            Text(text = apod.title, style = MaterialTheme.typography.headlineLarge)
            // Description
            Text(text = apod.explanation, style = MaterialTheme.typography.bodyMedium)
            // Photographer/Copyright
            Text(text = "© ${apod.copyright}", style = MaterialTheme.typography.labelSmall)
        }

}



