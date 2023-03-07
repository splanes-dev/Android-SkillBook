package com.splanes.apps.skillbook.data.feature.workhistory.dto

import com.google.gson.annotations.SerializedName

data class WorkHistoryDto(
    @SerializedName("history") val workEntries: List<WorkHistoryEntryDto>?
)
