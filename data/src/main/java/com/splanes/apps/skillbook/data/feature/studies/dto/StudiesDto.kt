package com.splanes.apps.skillbook.data.feature.studies.dto

import com.google.gson.annotations.SerializedName

data class StudiesDto(
    @SerializedName("studies") val studies: List<StudyEntryDto>?
)
