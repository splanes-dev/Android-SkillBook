package com.splanes.apps.skillbook.data.feature.workhistory.repository

import com.splanes.apps.skillbook.data.feature.workhistory.datasource.WorkHistoryCacheDataSource
import com.splanes.apps.skillbook.data.feature.workhistory.datasource.WorkHistoryRemoteDataSource
import com.splanes.apps.skillbook.data.feature.workhistory.dto.mapper.WorkHistoryDtoMapper
import com.splanes.apps.skillbook.domain.feature.workhistory.model.WorkHistoryData
import com.splanes.apps.skillbook.domain.feature.workhistory.repository.WorkHistoryRepository
import javax.inject.Inject

class WorkHistoryRepositoryImpl @Inject constructor(
    private val mapper: WorkHistoryDtoMapper,
    private val cacheDataSource: WorkHistoryCacheDataSource,
    private val remoteDataSource: WorkHistoryRemoteDataSource
) : WorkHistoryRepository {

    override suspend fun getWorkHistory(): WorkHistoryData {
        val workHistory =
            cacheDataSource.history ?: remoteDataSource.getWorkHistory().also(cacheDataSource::save)

        return workHistory.let(mapper::map)
    }
}
