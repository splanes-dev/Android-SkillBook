package com.splanes.apps.skillbook.data.feature.profile.datasource

import com.splanes.apps.skillbook.data.feature.profile.dto.ProfileDto

interface ProfileRemoteDataSource {

    suspend fun getProfile(): ProfileDto
}
