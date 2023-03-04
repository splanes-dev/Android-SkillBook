package com.splanes.apps.skillbook.domain.feature.profile.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProfileAboutMeData(
    val description: String
) : Parcelable
