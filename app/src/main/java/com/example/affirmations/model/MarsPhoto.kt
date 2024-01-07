package com.example.affirmations.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a photo from Mars.
 *
 * @property id The unique identifier of the Mars photo.
 * @property imgSrc The source URL of the Mars photo image.
 *      The @SerialName annotation indicates the actual name of this property in the JSON.
 */
@Serializable
data class MarsPhoto(
    val id: String,
    @SerialName(value = "img_src")
    val imgSrc: String
)
