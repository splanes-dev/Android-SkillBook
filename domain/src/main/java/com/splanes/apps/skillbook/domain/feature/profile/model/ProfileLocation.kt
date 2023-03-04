package com.splanes.apps.skillbook.domain.feature.profile.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProfileLocation(
    val description: String,
    val coordinates: String
) : Parcelable
