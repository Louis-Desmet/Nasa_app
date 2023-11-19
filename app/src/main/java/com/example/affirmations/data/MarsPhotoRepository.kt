package com.example.affirmations.data


import com.example.affirmations.model.MarsPhoto
import com.example.affirmations.network.NasaApi

interface MarsPhotosRepository {
    suspend fun getMarsPhotos(): List<MarsPhoto>
}

class NetworkMarsPhotosRepository : MarsPhotosRepository {
    override suspend fun getMarsPhotos(): List<MarsPhoto> {
        return NasaApi.retrofitService.getPhotos()
    }

}

class MarsPhotoRepository {
}
