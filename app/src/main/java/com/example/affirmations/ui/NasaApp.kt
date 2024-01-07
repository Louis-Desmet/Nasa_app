package com.example.affirmations.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.affirmations.Destinations
import com.example.affirmations.screens.APODScreen
import com.example.affirmations.screens.MarsPictureScreen
import com.example.affirmations.ui.APODViewModel
import com.example.affirmations.ui.MarsViewModel
/**
 * Composable function that sets up the navigation for the NASA app.
 *
 * @param viewModelMars ViewModel for Mars pictures screen.
 * @param apodViewModel ViewModel for APOD screen.
 * @param navController Navigation controller for managing app navigation.
 */
@Composable
fun NasaApp(
    viewModelMars: MarsViewModel,
    apodViewModel: APODViewModel,
    navController: NavHostController = rememberNavController()
) {

    Scaffold(
        bottomBar = { BottomBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Destinations.APODScreen,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Destinations.MarsPictureScreen) {
                AnimatedVisibility(
                    visible = true,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    MarsPictureScreen(
                        viewModel = viewModelMars,
                        marsUiState = viewModelMars.marsUiState,
                        retryAction = viewModelMars::getMarsImages
                    )
                }
            }
            composable(Destinations.APODScreen) {
                AnimatedVisibility(
                    visible = true,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    APODScreen(
                        apodUiState = apodViewModel.apodUiState,
                        retryAction = apodViewModel::getAPOD
                    )
                }
            }
        }
    }

}
/**
 * Composable function for the bottom navigation bar.
 *
 * @param navController Controller for navigation between screens.
 */
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