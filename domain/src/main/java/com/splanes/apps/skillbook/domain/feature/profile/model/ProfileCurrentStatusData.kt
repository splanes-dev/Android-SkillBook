package com.splanes.apps.skillbook.domain.feature.profile.model

import android.os.Parcelable
import com.splanes.apps.skillbook.domain.feature.workhistory.model.WorkHistoryEntryData
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProfileCurrentStatusData(
    val work: WorkHistoryEntryData,
    val status: ProfileCurrentStatus
) : Parcelable
