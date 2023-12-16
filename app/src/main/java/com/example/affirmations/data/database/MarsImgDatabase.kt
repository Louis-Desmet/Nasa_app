package com.example.affirmations.data.database

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [DbMarsImg::class], version=1)

abstract class MarsImgDatabase : RoomDatabase() {
    abstract fun MarsImgDao(): MarsImgDao
}