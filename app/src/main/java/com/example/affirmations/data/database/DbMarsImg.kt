package com.example.affirmations.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName

@Entity(tableName = "marsImages")
data class DbMarsImg(
    @PrimaryKey(autoGenerate = true)
    val id: String,
    @SerialName(value = "img_src")
    val imgSrc: String,
    val isDone: Boolean = false,
)