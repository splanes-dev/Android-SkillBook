package com.splanes.apps.skillbook.domain.feature.workhistory.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WorkHistoryData(
    val entries: List<WorkHistoryEntryData>
) : Parcelable
