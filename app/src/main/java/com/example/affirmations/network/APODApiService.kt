package com.example.affirmations.network

import com.example.affirmations.model.APOD
import retrofit2.http.GET
import retrofit2.http.Query

interface APODApiService {
    @GET("planetary/apod")
    suspend fun getAPOD(@Query("api_key") apiKey: String): APOD
}
