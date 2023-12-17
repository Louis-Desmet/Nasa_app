package com.example.fake

import com.example.affirmations.data.database.DbMarsImg
import com.example.affirmations.data.database.MarsImgDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.*

class FakeMarsImgDao : MarsImgDao {
    private val marsImgList = mutableListOf<DbMarsImg>()
    private val flow = MutableStateFlow<List<DbMarsImg>>(listOf())

    override suspend fun insert(item: DbMarsImg) {
        marsImgList.add(item)
        flow.value = marsImgList
    }

    override fun getAllItems(): Flow<List<DbMarsImg>> = flow.asStateFlow()

    override fun getItem(id: String): Flow<DbMarsImg> {
        return flow.map { list -> list.firstOrNull { it.id == id } ?: DbMarsImg("", "", false) }
    }

    /*
    override fun getItem(id: String): Flow<DbMarsImg> {
        val item = marsImgList.find { it.id == id }
        return flow.map { it.filter { img -> img.id == id } }
    }
    */

}
