package com.splanes.apps.skillbook.data.feature.workhistory.datasource

import com.splanes.apps.skillbook.data.feature.workhistory.dto.WorkHistoryDto

interface WorkHistoryRemoteDataSource {

    suspend fun getWorkHistory(): WorkHistoryDto
}
