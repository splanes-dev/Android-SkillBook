package com.splanes.apps.skillbook.data.feature.profile.datasource

import com.splanes.apps.skillbook.data.feature.profile.dto.ProfileDto

interface ProfileCacheDataSource {
    val profile: ProfileDto?
    fun save(profile: ProfileDto)
}