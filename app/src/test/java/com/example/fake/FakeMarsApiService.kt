package com.example.fake

import com.example.affirmations.model.MarsPhoto
import com.example.affirmations.network.MarsApiService

class FakeMarsApiService : MarsApiService {

    override suspend fun getPhotos(): List<MarsPhoto> {
        return FakeDataSource.photosList
    }

}
