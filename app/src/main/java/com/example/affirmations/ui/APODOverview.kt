package com.example.affirmations.ui

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
import androidx.compose.ui.Modifier
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

@Preview
@Composable
private fun APODCardPreview() {
    //APODCard(APOD(R.string.affirmation1, R.drawable.nasa1))
}

@Composable
fun APODList(apodList: List<APOD>, modifier: Modifier = Modifier, viewmodel:APODViewModel = viewModel()) {
    LazyColumn(modifier = modifier) {
        items(apodList) { apod ->
            APODCard(apod = apod, modifier = Modifier.padding(dimensionResource(id = R.dimen.padding)))

        }
    }
}
