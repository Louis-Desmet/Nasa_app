package com.example.affirmations.data


import com.example.affirmations.data.database.MarsImgDao
import com.example.affirmations.data.database.asDbMarsImg
import com.example.affirmations.data.database.asDomainMarsImage
import com.example.affirmations.model.MarsPhoto
import com.example.affirmations.network.MarsApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

interface MarsPhotoRepository {
    //suspend fun getMarsPhotos(): List<MarsPhoto>
    suspend fun insert(item: MarsPhoto)
    fun getMarsPhotos(): Flow<List<MarsPhoto>>
    fun getItem(id: Int): Flow<MarsPhoto>
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
            it.asDomainMarsImage()
        }.onEach {
            if (it.isEmpty()) {
                refresh()
            }
        }
    }

    override fun getItem(id: Int): Flow<MarsPhoto> {
       TODO("3NOG DOEN")
    }

        //marsfotos ophalen van het internet (via api service)
        override suspend fun refresh() {
            val marsPhotos = marsApiService.getPhotos()
            marsPhotos.forEach { marsPhoto ->
                insert(marsPhoto)
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





