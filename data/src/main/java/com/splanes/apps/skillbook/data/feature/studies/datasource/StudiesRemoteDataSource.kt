package com.splanes.apps.skillbook.data.feature.studies.datasource

import com.splanes.apps.skillbook.data.feature.studies.dto.StudiesDto

interface StudiesRemoteDataSource {

    suspend fun getStudies(): StudiesDto
}
