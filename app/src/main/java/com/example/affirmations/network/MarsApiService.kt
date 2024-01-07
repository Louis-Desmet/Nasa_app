package com.example.affirmations.network

import com.example.affirmations.model.MarsPhoto
import kotlinx.coroutines.flow.flow
import retrofit2.http.GET

/**
 * Interface for the Mars API service.
 * Defines methods for interacting with the Mars photo API.
 */
interface MarsApiService {
    /**
     * Retrieves a list of Mars photos.
     *
     * @return A list of [MarsPhoto] objects.
     */
    @GET("photos")
    suspend fun getPhotos() : List<MarsPhoto>
}

/**
 * Extension function for [MarsApiService].
 * Transforms the Mars photos API call into a Flow.
 *
 * @return A Flow emitting a list of Mars photos.
 */
fun MarsApiService.getMarsImagesAsFlow() = flow { emit(getPhotos())}
