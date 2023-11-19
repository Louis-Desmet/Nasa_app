package com.example.affirmations.network

import com.example.affirmations.model.MarsPhoto
import retrofit2.http.GET


interface MarsApiService {
    //url/photos ophalen
    @GET("photos")
    suspend fun getPhotos() : List<MarsPhoto>
}


