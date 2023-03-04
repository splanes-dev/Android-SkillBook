package com.splanes.apps.skillbook.domain.feature.profile.repository

import com.splanes.apps.skillbook.domain.feature.profile.model.ProfileData

interface ProfileRepository {
    suspend fun getProfile(): ProfileData
}
