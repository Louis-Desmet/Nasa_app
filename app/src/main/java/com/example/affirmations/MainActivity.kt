package com.example.affirmations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.affirmations.ui.APODViewModel
import com.example.affirmations.ui.MarsViewModel
import com.example.affirmations.ui.NasaApp
import com.example.affirmations.ui.theme.AffirmationsTheme

/**
 * Main activity for the application.
 * Sets up the UI and initializes the view models.
 */
class MainActivity : ComponentActivity() {

    /**
     * Called when the activity is starting.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously
     * being shut down then this Bundle contains the data it most recently supplied.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Setting the app theme
            AffirmationsTheme {
                // Instantiating view models
                val viewModelMars: MarsViewModel by viewModels { MarsViewModel.Factory }
                val apodViewModel: APODViewModel by viewModels { APODViewModel.Factory }

                // Main Composable function that sets up the app
                NasaApp(viewModelMars, apodViewModel)
            }
        }
    }
}
