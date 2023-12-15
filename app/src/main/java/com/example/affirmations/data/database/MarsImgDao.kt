package com.example.affirmations.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MarsImgDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: DbMarsImg)

    @Query("SELECT * from marsImages")
    fun getAllItems(): Flow<List<DbMarsImg>>

    @Query("SELECT * from marsImages where id = :id")
    fun getItem(id: Int): Flow<DbMarsImg>
}