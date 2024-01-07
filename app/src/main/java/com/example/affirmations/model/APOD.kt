package com.example.affirmations.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.serialization.Serializable

/**
 * Represents an Astronomy Picture of the Day (APOD) data model.
 *
 * @property title The title of the APOD.
 * @property explanation A description or explanation of the APOD.
 * @property url The URL of the APOD image.
 * @property hdurl The URL of the high-definition version of the APOD image.
 * @property copyright The copyright information of the APOD.
 * @property date The date of the APOD.
 * @property media_type The type of media (e.g., image, video) of the APOD.
 * @property service_version The version of the APOD service used.
 */
@Serializable
data class APOD(
    val title: String? = null,
    val explanation: String? = null,
    val url: String? = null,
    val hdurl: String? = null,
    val copyright: String? = null,
    val date: String? = null,
    val media_type: String? = null,
    val service_version: String? = null
)
