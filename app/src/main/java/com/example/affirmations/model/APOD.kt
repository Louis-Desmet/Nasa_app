package com.example.affirmations.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.serialization.Serializable

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

