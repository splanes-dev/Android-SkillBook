package com.splanes.apps.skillbook.data.feature.studies.dto

import com.google.gson.annotations.SerializedName

data class StudyEntryDto(
    @SerializedName("studyName") val studyName: String?,
    @SerializedName("school") val school: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("startDate") val startDate: String?,
    @SerializedName("endDate") val endDate: String?
)
