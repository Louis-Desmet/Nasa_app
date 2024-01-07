package com.example.affirmations.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.affirmations.R
import com.example.affirmations.model.MarsPhoto
import com.example.affirmations.ui.MarsUiState
import com.example.affirmations.ui.MarsViewModel

/**
 * Composable function to display the Mars Picture Screen.
 * It manages different UI states such as loading, success, and error.
 *
 * @param marsUiState The current state of the Mars UI.
 * @param retryAction Action to perform on retry.
 * @param modifier Modifier for this composable.
 * @param viewModel ViewModel to manage the UI state.
 */
@Composable
fun MarsPictureScreen(
    marsUiState: MarsUiState, retryAction: () -> Unit, modifier: Modifier = Modifier, viewModel: MarsViewModel
) {
    val photos by viewModel.uiListState.collectAsState()

    when (marsUiState) {
        is MarsUiState.Loading -> {
            LoadingScreen(modifier = modifier.fillMaxSize())
        }

        is MarsUiState.Success -> {
            PhotosGridScreen(photos = photos, modifier)
        }

        is MarsUiState.Error -> {
            ErrorScreen(retryAction, modifier = modifier.fillMaxSize())
        }
    }
}

/**
 * Composable function to display a grid of Mars photos.
 *
 * @param photos List of MarsPhoto to be displayed.
 * @param modifier Modifier for this composable.
 */
@Composable
fun PhotosGridScreen(photos: List<MarsPhoto>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = modifier.fillMaxWidth().testTag("PhotosGrid"),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(items = photos, key = { photo -> photo.id }) { photo ->
            MarsPhotoCard(
                photo,
                modifier = modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .aspectRatio(1.5f)
            )
        }
    }
}

/**
 * Composable function to display a single Mars photo card.
 *
 * @param photo MarsPhoto to be displayed.
 * @param modifier Modifier for this composable.
 */
@Composable
fun MarsPhotoCard(photo: MarsPhoto, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(photo.imgSrc)
                .crossfade(true)
                .build(),
            error = painterResource(R.drawable.ic_broken_image),
            placeholder = painterResource(R.drawable.loading_img),
            contentDescription = stringResource(R.string.mars_photo),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

/**
 * Composable function to display an error screen.
 *
 * @param retryAction Action to perform on retry.
 * @param modifier Modifier for this composable.
 */
@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}

/**
 * Composable function to display a loading screen.
 *
 * @param modifier Modifier for this composable.
 */
@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp).testTag("LoadingImage"),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
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

