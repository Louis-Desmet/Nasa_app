package com.example.affirmations.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.serialization.Serializable

@Serializable
data class APOD(
    val title: String,
    val explanation: String,
    val url: String,
    val hdurl: String,
    val copyright: String?,
    val date: String,
    val media_type: String,
    val service_version: String
)
