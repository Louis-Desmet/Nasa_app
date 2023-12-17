package com.example.affirmations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.affirmations.ui.APODViewModel
import com.example.affirmations.ui.MarsViewModel
import com.example.affirmations.ui.NasaApp
import com.example.affirmations.ui.theme.AffirmationsTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AffirmationsTheme {
                // Instantiate your view models here
                val viewModelMars: MarsViewModel by viewModels { MarsViewModel.Factory }
                val apodViewModel: APODViewModel by viewModels {APODViewModel.Factory}

                // Call NasaApp with the view models
                NasaApp(viewModelMars, apodViewModel)
            }
        }
    }
}
