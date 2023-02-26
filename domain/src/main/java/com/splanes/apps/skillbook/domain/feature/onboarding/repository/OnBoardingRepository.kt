package com.splanes.apps.skillbook.domain.feature.onboarding.repository

interface OnBoardingRepository {
    suspend fun isVisible(): Boolean

    suspend fun updateVisibility(isVisible: Boolean)
}
