package com.example.affirmations.data

import com.example.affirmations.model.APOD
import com.example.affirmations.network.APODApiService

interface APODRepository {
    suspend fun getAPOD(): APOD
}

class NetworkAPODRepository(
    private val apodApiService: APODApiService
): APODRepository{
    override suspend fun getAPOD(): APOD = apodApiService.getAPOD("v0o423qcdnmMgnBVGflgVSkR2HJCawNwg1MYp2DE")
}

