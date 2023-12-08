package com.example.affirmations.data

import com.example.affirmations.network.APODApiService
import com.example.affirmations.network.MarsApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val marsPhotoRepository : MarsPhotoRepository
    val apodRepository: APODRepository
}

class DefaultAppContainer : AppContainer {
    private val marsBaseUrl =
        "https://android-kotlin-fun-mars-server.appspot.com"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(marsBaseUrl)
        .build()

    private val retrofitService: MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }

    override val marsPhotoRepository: MarsPhotoRepository by lazy {
        NetworkMarsPhotoRepository(retrofitService)
    }

    //---

    private val apodBaseUrl = "https://api.nasa.gov/"

    private val apodRetrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(apodBaseUrl)
        .build()

    private val apodApiService: APODApiService by lazy {
        apodRetrofit.create(APODApiService::class.java)
    }

    override val apodRepository: APODRepository by lazy {
        NetworkAPODRepository(apodApiService)
    }



}