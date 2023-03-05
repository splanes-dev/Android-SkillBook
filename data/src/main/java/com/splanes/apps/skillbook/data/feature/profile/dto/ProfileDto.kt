package com.splanes.apps.skillbook.data.feature.profile.dto

import com.google.gson.annotations.SerializedName

data class ProfileDto(
    @SerializedName("contact") val contact: ProfileContactDto,
    @SerializedName("about-me") val aboutMe: ProfileAboutMeDto,
    @SerializedName("current-status") val currentStatus: ProfileCurrentStatusDto
)
