package com.example.affirmations.network

import com.example.affirmations.model.MarsPhoto
import kotlinx.coroutines.flow.flow

import retrofit2.http.GET


interface MarsApiService {
    //url/photos ophalen
    @GET("photos")
    suspend fun getPhotos() : List<MarsPhoto>
}

fun MarsApiService.getMarsImagesAsFlow() = flow { emit(getPhotos())}


