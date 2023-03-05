package com.splanes.apps.skillbook.data.feature.profile.datasource.impl

import com.splanes.apps.skillbook.data.feature.profile.datasource.ProfileCacheDataSource
import com.splanes.apps.skillbook.data.feature.profile.dto.ProfileDto

object ProfileCache : ProfileCacheDataSource {

    override var profile: ProfileDto? = null
        private set

    override fun save(profile: ProfileDto) {
        this.profile = profile
    }
}
