package com.example.affirmations.network

import com.example.affirmations.model.APOD
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit service interface for NASA's Astronomy Picture of the Day (APOD) API.
 *
 * This interface defines methods to interact with the APOD endpoints.
 */
interface APODApiService {

    /**
     * Retrieves the Astronomy Picture of the Day.
     *
     * @param apiKey The API key required to authenticate the request to the APOD API.
     * @return The [APOD] object containing the data for the Astronomy Picture of the Day.
     */
    @GET("planetary/apod")
    suspend fun getAPOD(@Query("api_key") apiKey: String): APOD
}
