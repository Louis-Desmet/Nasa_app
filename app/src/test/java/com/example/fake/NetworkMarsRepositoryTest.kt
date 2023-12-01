package com.example.fake

import com.example.affirmations.data.MarsPhotoRepository
import com.example.affirmations.data.NetworkMarsPhotoRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class NetworkMarsRepositoryTest {
    @Test
    fun networkMarsPhotosRepository_getMarsPhotos_verifyPhotoList() = runTest{
        val repository = NetworkMarsPhotoRepository(marsApiService = FakeMarsApiService()
        )
        assertEquals(FakeDataSource.photosList, repository.getMarsPhotos())

    }
}