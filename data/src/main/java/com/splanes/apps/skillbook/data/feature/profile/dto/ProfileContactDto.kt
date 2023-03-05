package com.splanes.apps.skillbook.data.feature.profile.dto

import com.google.gson.annotations.SerializedName

data class ProfileContactDto(
    @SerializedName("name") val name: String?,
    @SerializedName("imageUrl") val imageUrl: String?,
    @SerializedName("location") val location: ProfileLocationDto?,
    @SerializedName("email") val email: String?,
    @SerializedName("phone") val phone: String?,
    @SerializedName("linkedIn") val linkedInUrl: String?,
    @SerializedName("github") val githubUrl: String?
)
