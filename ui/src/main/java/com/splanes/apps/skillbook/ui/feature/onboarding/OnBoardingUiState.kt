package com.splanes.apps.skillbook.ui.feature.onboarding

import com.splanes.apps.skillbook.domain.common.error.KnownException
import com.splanes.apps.skillbook.ui.feature.onboarding.model.OnBoardingUiData

sealed interface OnBoardingUiState {

    data class Loading(
        val error: KnownException? = null
    ) : OnBoardingUiState

    data class DataLoaded(
        val error: KnownException? = null,
        val pages: List<OnBoardingUiData> = emptyList()
    ) : OnBoardingUiState
}

sealed interface OnBoardingUiSideEffect {
    object NavToDashboard : OnBoardingUiSideEffect
}
