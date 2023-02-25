package com.splanes.apps.skillbook.ui.feature.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun OnBoardingRoute(
    viewModel: OnBoardingViewModel,
    onNavToDashboard: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    OnBoardingRoute(
        uiState = uiState,
        onFinishOnBoarding = viewModel::onFinishOnBoarding
    )
}

@Composable
fun OnBoardingRoute(
    uiState: OnBoardingUiState,
    onFinishOnBoarding: () -> Unit
) {
    when (uiState) {
        is OnBoardingUiState.DataLoaded ->
            OnBoardingPagerScreen(
                uiState = uiState,
                onFinishOnBoarding = onFinishOnBoarding
            )

        OnBoardingUiState.Loading ->
            OnBoardingLoadingScreen()
    }
}
