package com.example.fake

import com.example.affirmations.data.MarsPhotoRepository
import com.example.affirmations.model.MarsPhoto

class FakeNetworkMarsPhotosRepository: MarsPhotoRepository {
    override suspend fun getMarsPhotos(): List<MarsPhoto> {
        return FakeDataSource.photosList
    }
}