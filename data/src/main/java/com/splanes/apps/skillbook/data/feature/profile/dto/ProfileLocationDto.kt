package com.splanes.apps.skillbook.data.feature.profile.dto

import com.google.gson.annotations.SerializedName

data class ProfileLocationDto(
    @SerializedName("description") val description: String?,
    @SerializedName("coordinates") val coordinates: String?
)
