package com.example.affirmations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.affirmations.ui.theme.AffirmationsTheme
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import com.example.affirmations.ui.MarsViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.affirmations.screens.APODCardPreview
import com.example.affirmations.screens.MarsPictureScreen


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AffirmationsTheme {
                val navController = rememberNavController()
                val viewModel: MarsViewModel by viewModels { MarsViewModel.Factory }
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
            icon = { Icon(Icons.Filled.Home, contentDescription = "APOD") },
            selected = navController.currentDestination?.route == Destinations.APODScreen,
            onClick = { navController.navigate(Destinations.APODScreen) }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.List, contentDescription = "Mars Pictures") },
            selected = navController.currentDestination?.route == Destinations.MarsPictureScreen,
            onClick = { navController.navigate(Destinations.MarsPictureScreen) }
        )

    }
}

