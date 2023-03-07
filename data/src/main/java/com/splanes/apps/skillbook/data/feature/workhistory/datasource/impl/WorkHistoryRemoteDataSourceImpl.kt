package com.splanes.apps.skillbook.data.feature.workhistory.datasource.impl

import com.splanes.apps.skillbook.data.common.firebase.FirebaseStorageHelper
import com.splanes.apps.skillbook.data.feature.workhistory.datasource.WorkHistoryRemoteDataSource
import com.splanes.apps.skillbook.data.feature.workhistory.dto.WorkHistoryDto
import com.splanes.apps.skillbook.domain.common.error.ConfigFileNotFound
import javax.inject.Inject

class WorkHistoryRemoteDataSourceImpl @Inject constructor(
    private val firebaseHelper: FirebaseStorageHelper
) : WorkHistoryRemoteDataSource {
    override suspend fun getWorkHistory(): WorkHistoryDto {
        val workHistory = firebaseHelper.downloadJson<WorkHistoryDto>(
            path = WorkHistoryPath,
            file = WorkHistoryJson
        )?.let { workHistory ->
            workHistory.copy(
                workEntries = workHistory.workEntries?.map { entry ->
                    entry.copy(
                        logoUrl = firebaseHelper.urlOf(
                            path = LogoPath,
                            file = entry.logoUrl.orEmpty()
                        )
                    )
                }
            )
        }

        return workHistory ?: throw ConfigFileNotFound(WorkHistoryJson)
    }
}

private const val LogoPath = "logo"
private const val WorkHistoryPath = "work"
private const val WorkHistoryJson = "work.json"
