package com.splanes.apps.skillbook.ui.feature.onboarding

import com.splanes.apps.skillbook.ui.feature.onboarding.model.OnBoardingUiData

sealed interface OnBoardingUiState {
    object Loading : OnBoardingUiState

    @JvmInline
    value class DataLoaded(val pages: List<OnBoardingUiData>) : OnBoardingUiState
}
