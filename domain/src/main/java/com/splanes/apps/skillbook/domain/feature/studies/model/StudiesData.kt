package com.splanes.apps.skillbook.domain.feature.studies.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StudiesData(
    val entries: List<StudyEntryData>
) : Parcelable
