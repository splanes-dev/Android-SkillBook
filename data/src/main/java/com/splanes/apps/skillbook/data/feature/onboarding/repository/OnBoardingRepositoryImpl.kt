package com.splanes.apps.skillbook.data.feature.onboarding.repository

import com.splanes.apps.skillbook.data.feature.onboarding.datasource.OnBoardingDataSource
import com.splanes.apps.skillbook.domain.feature.onboarding.repository.OnBoardingRepository
import javax.inject.Inject

class OnBoardingRepositoryImpl @Inject constructor(
    private val dataSource: OnBoardingDataSource
) : OnBoardingRepository {

    override suspend fun isVisible(): Boolean =
        dataSource.getVisibility()

    override suspend fun updateVisibility(isVisible: Boolean) =
        dataSource.updateOrInsertVisibility(isVisible)
}
