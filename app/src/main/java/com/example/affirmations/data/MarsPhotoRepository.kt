package com.example.affirmations.data

import android.util.Log
import com.example.affirmations.data.database.MarsImgDao
import com.example.affirmations.data.database.asDbMarsImg
import com.example.affirmations.data.database.asDomainMarsImage
import com.example.affirmations.data.database.asDomainMarsImages
import com.example.affirmations.model.MarsPhoto
import com.example.affirmations.network.MarsApiService
import com.example.affirmations.network.getMarsImagesAsFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

/**
 * Interface for the repository that handles operations related to Mars photos.
 */
interface MarsPhotoRepository {
    //suspend fun getMarsPhotos(): List<MarsPhoto>
    suspend fun insert(item: MarsPhoto)
    fun getMarsPhotos(): Flow<List<MarsPhoto>>
    fun getItem(id: String): Flow<MarsPhoto>
    suspend fun refresh()
}

/**
 * A [MarsPhotoRepository] implementation that caches Mars photos locally in a database
 * and fetches them from the network when needed.
 *
 * @property marsImgDao DAO for accessing the local database.
 * @property marsApiService Service for accessing Mars photos from the network.
 */
class CachingMarsPhotoRepository(
    private val marsImgDao: MarsImgDao,
    private val marsApiService: MarsApiService
) : MarsPhotoRepository {

    /**
     * Inserts a Mars photo into the local database.
     *
     * @param item The [MarsPhoto] to insert.
     */
    override suspend fun insert(item: MarsPhoto) {
        marsImgDao.insert(item.asDbMarsImg())
    }

    /**
     * Retrieves all Mars photos as a Flow.
     *
     * @return A Flow of a list of [MarsPhoto].
     */
    override fun getMarsPhotos(): Flow<List<MarsPhoto>> {
        return marsImgDao.getAllItems().map {
            it.asDomainMarsImages()
        }.onEach {
            if (it.isEmpty()) {
                refresh()
            }
        }
    }

    /**
     * Retrieves a single Mars photo by its ID as a Flow.
     *
     * @param id The ID of the Mars photo to retrieve.
     * @return A Flow of the requested [MarsPhoto].
     */
    override fun getItem(id: String): Flow<MarsPhoto> {
        return marsImgDao.getItem(id).map {
            it.asDomainMarsImage()
        }
    }

    /**
     * Refreshes the local cache of Mars photos by fetching them from the network.
     */
    override suspend fun refresh() {
        marsApiService.getMarsImagesAsFlow().collect {
            for (marsImage in it) {
                //Log.i("TEST","refresh: $marsImage")
                insert(marsImage)
            }
        }
    }
}

/*
class NetworkMarsPhotoRepository(
    private val marsApiService: MarsApiService
) : MarsPhotoRepository {
    /** Fetches list of MarsPhoto from marsApi*/
    override suspend fun getMarsPhotos(): List<MarsPhoto> = marsApiService.getPhotos()
}
*/
