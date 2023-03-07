package com.splanes.apps.skillbook.data.feature.workhistory.dto

import com.google.gson.annotations.SerializedName

data class WorkHistoryEntryDto(
    @SerializedName("enterprise") val enterprise: String?,
    @SerializedName("charge") val charge: String?,
    @SerializedName("logoUrl") val logoUrl: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("startDate") val startDate: String?,
    @SerializedName("endDate") val endDate: String?
)
