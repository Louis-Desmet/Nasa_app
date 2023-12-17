package com.example.marsphotos

import com.example.affirmations.data.CachingMarsPhotoRepository
import com.example.fake.FakeMarsApiService
import com.example.fake.FakeMarsImgDao
import com.example.affirmations.model.MarsPhoto
import com.example.fake.FakeDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import junit.framework.TestCase.assertEquals

class CachingMarsPhotoRepositoryTest {

    private lateinit var fakeMarsImgDao: FakeMarsImgDao
    private lateinit var fakeMarsApiService: FakeMarsApiService
    private lateinit var repository: CachingMarsPhotoRepository

    @Before
    fun setup() {
        fakeMarsImgDao = FakeMarsImgDao()
        fakeMarsApiService = FakeMarsApiService()
        repository = CachingMarsPhotoRepository(fakeMarsImgDao, fakeMarsApiService)
    }

    @Test
    fun cachingMarsPhotoRepository_getMarsPhotos_verifyPhotoList() = runTest {
        repository.refresh()
        val photos = repository.getMarsPhotos().first()

        assertEquals(FakeDataSource.photosList, photos)
    }
}
