package com.splanes.apps.skillbook.domain.feature.profile.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProfileContactData(
    val name: String,
    val location: ProfileLocation,
    val email: String,
    val phone: String,
    val profileImageUrl: String,
    val linkedInUrl: String,
    val githubUrl: String
) : Parcelable
