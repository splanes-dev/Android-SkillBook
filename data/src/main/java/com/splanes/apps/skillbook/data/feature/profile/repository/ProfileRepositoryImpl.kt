package com.splanes.apps.skillbook.data.feature.profile.repository

import com.splanes.apps.skillbook.data.feature.profile.datasource.ProfileCacheDataSource
import com.splanes.apps.skillbook.data.feature.profile.datasource.ProfileRemoteDataSource
import com.splanes.apps.skillbook.data.feature.profile.dto.mapper.ProfileDtoMapper
import com.splanes.apps.skillbook.domain.feature.profile.model.ProfileData
import com.splanes.apps.skillbook.domain.feature.profile.repository.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val mapper: ProfileDtoMapper,
    private val remoteDataSource: ProfileRemoteDataSource,
    private val cacheDataSource: ProfileCacheDataSource
) : ProfileRepository {

    override suspend fun getProfile(): ProfileData {
        val profile =
            cacheDataSource.profile ?: remoteDataSource.getProfile().also(cacheDataSource::save)

        return profile.let(mapper::map)
    }
}
