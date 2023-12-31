package com.example.affirmations.data

import android.content.Context
import androidx.room.Room
import com.example.affirmations.data.database.MarsImgDao
import com.example.affirmations.data.database.MarsImgDatabase
import com.example.affirmations.network.APODApiService
import com.example.affirmations.network.MarsApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

/**
 * Interface for the application container, holding repositories for Mars photos and APOD (Astronomy Picture of the Day).
 */
interface AppContainer {
    val marsPhotoRepository: MarsPhotoRepository
    val apodRepository: APODRepository
}

/**
 * Default implementation of [AppContainer].
 * Provides dependencies for MarsPhotoRepository and APODRepository.
 *
 * @param applicationContext The context of the application.
 */
class DefaultAppContainer(
    private val applicationContext: Context) : AppContainer {

    private val marsBaseUrl = "https://android-kotlin-fun-mars-server.appspot.com"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(marsBaseUrl)
        .build()

    private val retrofitService: MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }

    private val marsImgDb: MarsImgDatabase by lazy {
        Room.databaseBuilder(applicationContext, MarsImgDatabase::class.java, "marsImg_database").build()
    }

    private val marsImgDao: MarsImgDao by lazy {
        marsImgDb.MarsImgDao()
    }

    /**
     * Lazy-initialized repository for Mars photos.
     */
    override val marsPhotoRepository: MarsPhotoRepository by lazy {
        //NetworkMarsPhotoRepository(retrofitService)
        CachingMarsPhotoRepository(marsImgDao = marsImgDao, marsApiService = retrofitService)
    }

    private val apodBaseUrl = "https://api.nasa.gov/"

    private val apodRetrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(apodBaseUrl)
        .build()

    private val apodApiService: APODApiService by lazy {
        apodRetrofit.create(APODApiService::class.java)
    }

    /**
     * Lazy-initialized repository for APOD (Astronomy Picture of the Day).
     */
    override val apodRepository: APODRepository by lazy {
        NetworkAPODRepository(apodApiService)
    }
}
