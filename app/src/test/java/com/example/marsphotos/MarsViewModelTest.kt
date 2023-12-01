package com.example.marsphotos
import com.example.affirmations.ui.APODViewModel
import com.example.affirmations.ui.MarsUiState
import com.example.fake.FakeDataSource
import com.example.fake.FakeNetworkMarsPhotosRepository
import com.example.rules.TestDispatcherRule
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test


class MarsViewModelTest {
    @get:Rule
    val testDispatcher = TestDispatcherRule()
    @Test
    fun marsViewModel_getMarsPhotos_verifyMarsUiStateSuccess() = runTest{
        val marsViewModel = APODViewModel(marsPhotoRepository = FakeNetworkMarsPhotosRepository()
        )
        assertEquals(MarsUiState.Success("Success: ${FakeDataSource.photosList.size} Mars " + "photos retrieved"),
            marsViewModel.marsUiState)
    }
}






