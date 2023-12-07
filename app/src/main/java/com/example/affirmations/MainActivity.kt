/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.affirmations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.affirmations.model.APOD
import com.example.affirmations.ui.theme.AffirmationsTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import com.example.affirmations.ui.APODViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.affirmations.data.Datasource
import com.example.affirmations.screens.APODCardPreview
import com.example.affirmations.screens.HelloWorldScreen
import com.example.affirmations.screens.MarsPictureScreen
import com.example.affirmations.ui.APODState


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AffirmationsTheme {
                val navController = rememberNavController()
                val viewModel: APODViewModel by viewModels { APODViewModel.Factory }
                Scaffold(
                    bottomBar = { BottomBar(navController) }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Destinations.APODScreen,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(Destinations.MarsPictureScreen) {
                            MarsPictureScreen(
                            marsUiState = viewModel.marsUiState, retryAction = viewModel::getMarsImages
                            )
                        }
                        composable(Destinations.APODScreen) {
                            //HelloWorldScreen()
                            APODCardPreview()
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun BottomBar(navController: NavController) {
    BottomAppBar {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Star, contentDescription = "APOD") },
            selected = navController.currentDestination?.route == Destinations.APODScreen,
            onClick = { navController.navigate(Destinations.APODScreen) }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Mars Pictures") },
            selected = navController.currentDestination?.route == Destinations.MarsPictureScreen,
            onClick = { navController.navigate(Destinations.MarsPictureScreen) }
        )

    }
}

@Composable
fun NasaApp(viewModel: APODViewModel = viewModel()) {

    //APODList()

    MarsPictureScreen(marsUiState = viewModel.marsUiState, retryAction = viewModel::getMarsImages)


    //bottom bar, nav controller op dit niveau
}
