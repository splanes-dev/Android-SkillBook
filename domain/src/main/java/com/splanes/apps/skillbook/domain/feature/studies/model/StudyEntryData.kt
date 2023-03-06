package com.splanes.apps.skillbook.domain.feature.studies.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StudyEntryData(
    val school: String,
    val studyName: String,
    val description: String,
    val startDate: String,
    val endDate: String
) : Parcelable
