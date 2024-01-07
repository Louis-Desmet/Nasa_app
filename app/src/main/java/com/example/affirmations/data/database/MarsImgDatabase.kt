package com.example.affirmations.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * The Room database for this app, providing the main access point to the persisted data.
 *
 * This abstract class extends [RoomDatabase] and provides DAOs (Data Access Objects) for the application.
 * It is annotated with [@Database], which includes the list of entities and the version of the database.
 */
@Database(entities = [DbMarsImg::class], version = 1)
abstract class MarsImgDatabase : RoomDatabase() {

    /**
     * Gets the [MarsImgDao] for this database.
     *
     * @return The [MarsImgDao] for accessing Mars image data.
     */
    abstract fun MarsImgDao(): MarsImgDao
}
