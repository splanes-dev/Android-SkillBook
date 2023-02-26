package com.splanes.apps.skillbook.data.feature.onboarding.datasource

interface OnBoardingDataSource {
    suspend fun getVisibility(): Boolean
    suspend fun updateOrInsertVisibility(isVisible: Boolean)
}
