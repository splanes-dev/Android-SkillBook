package com.splanes.apps.skillbook.data.feature.studies.datasource.impl

import com.splanes.apps.skillbook.data.common.firebase.FirebaseStorageHelper
import com.splanes.apps.skillbook.data.feature.studies.datasource.StudiesRemoteDataSource
import com.splanes.apps.skillbook.data.feature.studies.dto.StudiesDto
import com.splanes.apps.skillbook.domain.common.error.ConfigFileNotFound
import javax.inject.Inject

class StudiesRemoteDataSourceImpl @Inject constructor(
    private val firebaseHelper: FirebaseStorageHelper
) : StudiesRemoteDataSource {

    override suspend fun getStudies(): StudiesDto {
        val studies = firebaseHelper.downloadJson<StudiesDto>(
            path = StudiesPath,
            file = StudiesJson
        )
        return studies ?: throw ConfigFileNotFound(StudiesJson)
    }
}

private const val StudiesPath = "studies"
private const val StudiesJson = "studies.json"
