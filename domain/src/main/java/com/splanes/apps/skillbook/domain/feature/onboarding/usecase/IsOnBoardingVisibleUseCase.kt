package com.splanes.apps.skillbook.domain.feature.onboarding.usecase

import com.splanes.apps.skillbook.domain.common.base.UseCase
import com.splanes.apps.skillbook.domain.feature.onboarding.repository.OnBoardingRepository
import javax.inject.Inject

class IsOnBoardingVisibleUseCase @Inject constructor(
    private val repository: OnBoardingRepository
) : UseCase<Unit, Boolean>() {

    override suspend fun execute(params: Unit): Boolean {
        val isVisible = repository.isVisible()
        if (isVisible) {
            repository.updateVisibility(isVisible = false)
        }
        return isVisible
    }
}
