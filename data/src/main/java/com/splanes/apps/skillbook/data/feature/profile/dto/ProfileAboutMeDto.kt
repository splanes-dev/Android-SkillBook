package com.splanes.apps.skillbook.data.feature.profile.dto

import com.google.gson.annotations.SerializedName

data class ProfileAboutMeDto(
    @SerializedName("description") val description: String?
)
