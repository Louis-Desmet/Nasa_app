package com.example.affirmations.data


import com.example.affirmations.model.MarsPhoto
import com.example.affirmations.network.MarsApiService

interface MarsPhotoRepository {
    suspend fun getMarsPhotos(): List<MarsPhoto>
}

class NetworkMarsPhotoRepository(
    private val marsApiService: MarsApiService
) : MarsPhotoRepository {
    /** Fetches list of MarsPhoto from marsApi*/
    override suspend fun getMarsPhotos(): List<MarsPhoto> = marsApiService.getPhotos()
}


