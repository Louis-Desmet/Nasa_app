package ui
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.affirmations.data.MarsPhotoRepository
import com.example.affirmations.model.MarsPhoto
import com.example.affirmations.screens.ErrorScreen
import com.example.affirmations.screens.LoadingScreen
import com.example.affirmations.screens.PhotosGridScreen
import com.example.affirmations.ui.MarsViewModel
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@RunWith(AndroidJUnit4::class)
class MarsPictureScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun marsPictureScreen_LoadingState_ShowsLoadingImage() {
        composeTestRule.setContent {
            LoadingScreen(modifier = Modifier.fillMaxSize())
        }
        // Check if the loading image is displayed
        composeTestRule.onNodeWithTag("LoadingImage").assertIsDisplayed()
    }


    @Test
    fun marsPictureScreen_SuccessState_ShowsPhotosGrid() {

        val mockPhotos = listOf(
            MarsPhoto(id = "1", imgSrc = "https://dummyimage.com/600x400/000/fff&text=Mars1"),
            MarsPhoto(id = "2", imgSrc = "https://dummyimage.com/600x400/000/fff&text=Mars2")

        )

        // Create a mock MarsPhotoRepository
        val mockRepository = mock(MarsPhotoRepository::class.java)
        `when`(mockRepository.getMarsPhotos()).thenReturn(flowOf(mockPhotos))

        // Create a mock ViewModel with the mocked repository
        val viewModel = MarsViewModel(marsPhotoRepository = mockRepository)


        composeTestRule.setContent {
            PhotosGridScreen(photos = mockPhotos, modifier = Modifier.fillMaxWidth())
        }

        // Check if displayed
        composeTestRule.onNodeWithTag("PhotosGrid").assertIsDisplayed()
    }



    @Test
    fun marsPictureScreen_ErrorState_ShowsRetryButton() {
        composeTestRule.setContent {
            ErrorScreen(retryAction = {}, modifier = Modifier.fillMaxSize())
        }

        // Check if the retry button
        composeTestRule.onNodeWithText("Retry").assertIsDisplayed()
    }
}
