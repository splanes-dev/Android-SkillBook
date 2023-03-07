package com.splanes.apps.skillbook.data.feature.workhistory.datasource

import com.splanes.apps.skillbook.data.feature.workhistory.dto.WorkHistoryDto

interface WorkHistoryCacheDataSource {
    val history: WorkHistoryDto?

    fun save(history: WorkHistoryDto)
}
