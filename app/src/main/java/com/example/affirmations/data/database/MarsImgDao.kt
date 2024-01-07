package com.example.affirmations.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) for accessing Mars image data.
 * This interface provides methods for interacting with the marsImages table.
 */
@Dao
interface MarsImgDao {

    /**
     * Inserts a [DbMarsImg] item into the database. If the item already exists, it replaces the old item.
     *
     * @param item The [DbMarsImg] item to be inserted into the database.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: DbMarsImg)

    /**
     * Retrieves all Mars image items from the database as a Flow.
     *
     * @return A Flow emitting the list of all Mars images in the database.
     */
    @Query("SELECT * from marsImages")
    fun getAllItems(): Flow<List<DbMarsImg>>

    /**
     * Retrieves a specific Mars image item by its ID as a Flow.
     *
     * @param id The ID of the Mars image item to be retrieved.
     * @return A Flow emitting the Mars image item with the given ID.
     */
    @Query("SELECT * from marsImages where id = :id")
    fun getItem(id: String): Flow<DbMarsImg>
}
