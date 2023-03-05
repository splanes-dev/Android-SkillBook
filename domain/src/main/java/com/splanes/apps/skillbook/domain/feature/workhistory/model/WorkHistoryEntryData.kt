package com.splanes.apps.skillbook.domain.feature.workhistory.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WorkHistoryEntryData(
    val enterprise: String,
    val charge: String,
    val description: String,
    val iconUrl: String,
    val startDate: String,
    val endDate: String
) : Parcelable
