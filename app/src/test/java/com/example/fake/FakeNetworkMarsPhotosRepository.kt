package com.example.fake

import com.example.affirmations.data.MarsPhotoRepository
import com.example.affirmations.model.MarsPhoto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map

class FakeNetworkMarsPhotosRepository : MarsPhotoRepository {
    private val photos = MutableStateFlow<List<MarsPhoto>>(FakeDataSource.photosList)

    override suspend fun insert(item: MarsPhoto) {
        val updatedList = photos.value.toMutableList().apply { add(item) }
        photos.value = updatedList
    }

    override fun getMarsPhotos(): Flow<List<MarsPhoto>> {
        return photos.asStateFlow()
    }

    override fun getItem(id: String): Flow<MarsPhoto> {
        return photos.map { it.firstOrNull { photo -> photo.id == id } ?: MarsPhoto("", "") }
    }

    override suspend fun refresh() {
        photos.value = FakeDataSource.photosList
    }
}
