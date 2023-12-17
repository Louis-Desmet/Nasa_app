import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.performClick
import com.example.affirmations.MainActivity
import org.junit.Rule
import org.junit.Test

class NavigationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testStartDestination() {

        composeTestRule.onNodeWithContentDescription("APOD")
            .assertIsDisplayed()
    }

    @Test
    fun testNavigationToMarsPicturesScreen() {
        // Click
        composeTestRule.onNodeWithContentDescription("Mars Pictures")
            .performClick()


        composeTestRule.onNodeWithContentDescription("Mars Pictures")
            .assertIsDisplayed()
    }

    @Test
    fun testNavigationToAPODScreen() {

        composeTestRule.onNodeWithContentDescription("APOD")
            .performClick()

        // Verify
        composeTestRule.onNodeWithContentDescription("APOD")
            .assertIsDisplayed()
    }
}
