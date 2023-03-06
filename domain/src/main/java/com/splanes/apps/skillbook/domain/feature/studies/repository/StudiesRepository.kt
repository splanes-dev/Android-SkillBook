package com.splanes.apps.skillbook.domain.feature.studies.repository

import com.splanes.apps.skillbook.domain.feature.studies.model.StudiesData

interface StudiesRepository {
    suspend fun getStudies(): StudiesData
}
