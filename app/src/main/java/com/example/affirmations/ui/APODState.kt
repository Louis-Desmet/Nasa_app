package com.example.affirmations.ui

import androidx.annotation.StringRes
import com.example.affirmations.model.APOD

//Irrelevant class?
data class APODState(
    //@StringRes val stringResourceId: Int,
    //@DrawableRes val imageResourceId: Int,
    val apods: List<APOD> = emptyList(),
    val isLoading: Boolean = true,
    val error: String? = null
)


