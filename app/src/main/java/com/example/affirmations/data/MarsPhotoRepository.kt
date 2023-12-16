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

interface MarsPhotoRepository {
    //suspend fun getMarsPhotos(): List<MarsPhoto>
    suspend fun insert(item: MarsPhoto)
    fun getMarsPhotos(): Flow<List<MarsPhoto>>
    fun getItem(id: String): Flow<MarsPhoto>
    suspend fun refresh()

}
class CachingMarsPhotoRepository(
    private val marsImgDao: MarsImgDao,
    private val marsApiService: MarsApiService
) : MarsPhotoRepository {
    override suspend fun insert(item: MarsPhoto) {
        marsImgDao.insert(item.asDbMarsImg())
    }

    override fun getMarsPhotos(): Flow<List<MarsPhoto>> {
        return marsImgDao.getAllItems().map {
            it.asDomainMarsImages()
        }.onEach {
            if (it.isEmpty()) {
                refresh()
            }
        }
    }

    override fun getItem(id: String): Flow<MarsPhoto> {
       return marsImgDao.getItem(id).map {
           it.asDomainMarsImage()
       }
    }

        //marsfotos ophalen van het internet (via api service)
        override suspend fun refresh() {
            marsApiService.getMarsImagesAsFlow().collect {
                for (marsImage in it) {
                    Log.i("TEST","refresh: $marsImage")
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





