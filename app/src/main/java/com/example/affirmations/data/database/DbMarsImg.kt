package com.example.affirmations.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.affirmations.model.MarsPhoto
import kotlinx.serialization.SerialName

/**
 * Data class representing a single image entity from Mars within the database.
 *
 * @property id The unique identifier for the Mars image.
 * @property imgSrc The source URL of the Mars image.
 * @property isDone A Boolean value indicating whether the image processing is completed.
 */
@Entity(tableName = "marsImages")
data class DbMarsImg(
    @PrimaryKey
    val id: String,
    @SerialName(value = "img_src")
    val imgSrc: String,
    val isDone: Boolean = false,
)

/**
 * Converts a [MarsPhoto] to a [DbMarsImg] entity.
 *
 * @return Returns a [DbMarsImg] entity with the same properties values as this [MarsPhoto].
 */
fun MarsPhoto.asDbMarsImg() = DbMarsImg(imgSrc = imgSrc, id = id)

/**
 * Converts a [DbMarsImg] entity to a domain model [MarsPhoto].
 *
 * @return Returns a domain model [MarsPhoto] with the same property values as this entity.
 */
fun DbMarsImg.asDomainMarsImage() = MarsPhoto(imgSrc = imgSrc, id=id )

/**
 * Converts a list of [DbMarsImg] entities to a list of domain model [MarsPhoto]s.
 *
 * @return Returns a list of domain model [MarsPhoto]s with the same property values as the entities in this list.
 */
fun List<DbMarsImg>.asDomainMarsImages() = map { it.asDomainMarsImage() }
