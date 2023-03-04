package com.splanes.apps.skillbook.domain.feature.profile.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProfileData(
    val aboutMe: ProfileAboutMeData,
    val contact: ProfileContactData,
    val currentStatus: ProfileCurrentStatusData
) : Parcelable
