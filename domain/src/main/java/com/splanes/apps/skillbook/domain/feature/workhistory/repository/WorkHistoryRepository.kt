package com.splanes.apps.skillbook.domain.feature.workhistory.repository

import com.splanes.apps.skillbook.domain.feature.workhistory.model.WorkHistoryData

interface WorkHistoryRepository {
    suspend fun getWorkHistory(): WorkHistoryData
}
