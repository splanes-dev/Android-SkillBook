package com.splanes.apps.skillbook.data.feature.studies.repository

import com.splanes.apps.skillbook.data.feature.studies.datasource.StudiesCacheDataSource
import com.splanes.apps.skillbook.data.feature.studies.datasource.StudiesRemoteDataSource
import com.splanes.apps.skillbook.data.feature.studies.dto.mapper.StudiesDtoMapper
import com.splanes.apps.skillbook.domain.feature.studies.model.StudiesData
import com.splanes.apps.skillbook.domain.feature.studies.repository.StudiesRepository
import javax.inject.Inject

class StudiesRepositoryImpl @Inject constructor(
    private val mapper: StudiesDtoMapper,
    private val remoteDataSource: StudiesRemoteDataSource,
    private val cacheDataSource: StudiesCacheDataSource
) : StudiesRepository {

    override suspend fun getStudies(): StudiesData {
        val studies =
            cacheDataSource.studies ?: remoteDataSource.getStudies().also(cacheDataSource::save)

        return studies.let(mapper::map)
    }
}
