package com.example.affirmations.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.affirmations.model.MarsPhoto
import kotlinx.serialization.SerialName

@Entity(tableName = "marsImages")
data class DbMarsImg(
    @PrimaryKey
    val id: String,
    @SerialName(value = "img_src")
    val imgSrc: String,
    val isDone: Boolean = false,
)

fun MarsPhoto.asDbMarsImg() = DbMarsImg(imgSrc = imgSrc, id = id)

fun DbMarsImg.asDomainMarsImage() = MarsPhoto(imgSrc = imgSrc, id=id )
fun List<DbMarsImg>.asDomainMarsImages() = map { it.asDomainMarsImage() }

