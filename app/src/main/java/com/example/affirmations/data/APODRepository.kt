package com.example.affirmations.data

import com.example.affirmations.model.APOD
import com.example.affirmations.network.APODApiService

/**
 * Repository interface for fetching Astronomy Picture of the Day (APOD) data.
 */
interface APODRepository {
    /**
     * Suspends the execution of the current coroutine and retrieves the APOD data.
     *
     * @return The [APOD] object containing the data for the Astronomy Picture of the Day.
     */
    suspend fun getAPOD(): APOD
}

/**
 * A [APODRepository] implementation that fetches APOD data over network using [APODApiService].
 *
 * @property apodApiService The service used to fetch APOD data from the network.
 */
class NetworkAPODRepository(
    private val apodApiService: APODApiService
) : APODRepository {

    /**
     * Retrieves the APOD data by making a network call through [APODApiService].
     *
     * Note: In a production environment, API keys should not be hard-coded but rather fetched
     * securely from a server or a secure storage mechanism.
     *
     * @return The [APOD] object obtained from the network.
     */
    override suspend fun getAPOD(): APOD = apodApiService.getAPOD("v0o423qcdnmMgnBVGflgVSkR2HJCawNwg1MYp2DE")
}
