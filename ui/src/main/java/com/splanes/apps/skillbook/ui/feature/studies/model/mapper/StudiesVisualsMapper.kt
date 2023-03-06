package com.splanes.apps.skillbook.ui.feature.studies.model.mapper

import com.splanes.apps.skillbook.domain.feature.studies.model.StudiesData
import com.splanes.apps.skillbook.domain.feature.studies.model.StudyEntryData
import com.splanes.apps.skillbook.ui.feature.studies.model.StudyEntryVisuals
import javax.inject.Inject

class StudiesVisualsMapper @Inject constructor() {

    fun map(studies: StudiesData): List<StudyEntryVisuals> = studies.entries.map(::map)

    private fun map(studies: StudyEntryData): StudyEntryVisuals =
        StudyEntryVisuals(
            studyName = studies.studyName,
            school = studies.school,
            description = studies.description,
            startDate = studies.startDate,
            endDate = studies.endDate.ifBlank { null }
        )
}
