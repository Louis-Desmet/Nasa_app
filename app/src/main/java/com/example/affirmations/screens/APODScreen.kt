package com.example.affirmations.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.affirmations.R
import com.example.affirmations.model.APOD
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.affirmations.ui.APODViewModel

@Composable
fun HelloWorldScreen() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Text(text = "Hello World")
    }
}

@Composable
fun APODCard(apod: APOD, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column {
            Image(
                painter = painterResource(apod.imageResourceId),
                contentDescription = stringResource(
                    apod.stringResourceId
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp), contentScale = ContentScale.Crop
            )
            Text(
                text = LocalContext.current.getString(apod.stringResourceId),
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineSmall
            )

        }
    }
}


@Composable
fun APODCardPreview() {
    APODCard(APOD(R.string.affirmation1, R.drawable.nasa1))
}

@Composable
fun APODList( modifier: Modifier = Modifier, viewmodel: APODViewModel = viewModel()) {
    val APODState by viewmodel.uiState.collectAsState()
    LazyColumn(modifier = modifier) {
        items(APODState.apods) { apod ->
            APODCard(apod = apod, modifier = Modifier.padding(dimensionResource(id = R.dimen.padding)))

        }
    }
}

