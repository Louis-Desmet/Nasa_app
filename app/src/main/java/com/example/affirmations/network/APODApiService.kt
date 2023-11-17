package com.example.affirmations.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET


private const val BASE_URL =
    "https://android-kotlin-fun-mars-server.appspot.com"

private val retrofit =
    Retrofit.Builder().addConverterFactory(ScalarsConverterFactory.create()).baseUrl(BASE_URL)
        .build()

interface APODApiService {
    //url/photos ophalen
    @GET("photos")
    suspend fun getPhotos() : String
}

object APODApi {
    val retrofitService : APODApiService by lazy {
        retrofit.create(APODApiService::class.java)
    }
}

