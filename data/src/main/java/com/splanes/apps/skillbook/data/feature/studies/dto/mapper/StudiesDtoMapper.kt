package com.splanes.apps.skillbook.data.feature.studies.dto.mapper

import com.splanes.apps.skillbook.data.feature.studies.dto.StudiesDto
import com.splanes.apps.skillbook.data.feature.studies.dto.StudyEntryDto
import com.splanes.apps.skillbook.domain.feature.studies.model.StudiesData
import com.splanes.apps.skillbook.domain.feature.studies.model.StudyEntryData
import javax.inject.Inject

class StudiesDtoMapper @Inject constructor() {

    fun map(dto: StudiesDto): StudiesData =
        StudiesData(entries = dto.studies?.map(::map).orEmpty())

    private fun map(dto: StudyEntryDto): StudyEntryData =
        StudyEntryData(
            school = dto.school.orEmpty(),
            studyName = dto.studyName.orEmpty(),
            description = dto.description.orEmpty(),
            startDate = dto.startDate.orEmpty(),
            endDate = dto.endDate.orEmpty()
        )
}
