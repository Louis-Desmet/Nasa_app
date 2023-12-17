package com.example.navigation

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.affirmations.Destinations
import com.example.affirmations.data.APODRepository
import com.example.affirmations.data.MarsPhotoRepository
import com.example.affirmations.ui.MarsViewModel
import com.example.affirmations.ui.APODViewModel
import com.example.affirmations.ui.NasaApp
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

class NavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var navController: TestNavHostController

    @Before
    fun setupNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())

            // Provide fake view models as necessary
            val viewModelMars = MarsViewModel(MarsPhotoRepository)
            val apodViewModel = APODViewModel(APODRepository)

            NasaApp(viewModelMars, apodViewModel, navController)
        }
    }

    @Test
    fun testNavigationToMarsPicturesScreen() {
        // Perform navigation action
        composeTestRule
            .onNodeWithContentDescription("Mars Pictures")
            .performClick()

        assert(navController.currentDestination?.route == Destinations.MarsPictureScreen)
    }

    @Test
    fun testNavigationToAPODScreen() {
        // Perform navigation action
        composeTestRule
            .onNodeWithContentDescription("APOD")
            .performClick()

        // Verify that the current route is updated
        assert(navController.currentDestination?.route == Destinations.APODScreen)
    }
}
