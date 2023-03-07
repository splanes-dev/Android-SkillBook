package com.splanes.apps.skillbook.data.feature.workhistory.datasource.impl

import com.splanes.apps.skillbook.data.feature.workhistory.datasource.WorkHistoryCacheDataSource
import com.splanes.apps.skillbook.data.feature.workhistory.dto.WorkHistoryDto

object WorkHistoryCache : WorkHistoryCacheDataSource {

    override var history: WorkHistoryDto? = null
    override fun save(history: WorkHistoryDto) {
        this.history = history
    }
}
