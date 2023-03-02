package com.splanes.apps.skillbook.ui.feature.onboarding

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun OnBoardingRoute(
    viewModel: OnBoardingViewModel,
    onNavToDashboard: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val uiSideEffect by viewModel.uiSideEffect.collectAsStateWithLifecycle()

    LaunchedEffect(uiSideEffect) {
        when (uiSideEffect) {
            OnBoardingUiSideEffect.NavToDashboard -> onNavToDashboard()
            null -> {
                /* Nothing to do here */
            }
        }
    }

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
    Crossfade(targetState = uiState, label = "OnBoardingRoute") { state ->
        when (state) {
            is OnBoardingUiState.DataLoaded ->
                OnBoardingPagerScreen(
                    uiState = state,
                    onFinishOnBoarding = onFinishOnBoarding
                )

            is OnBoardingUiState.Loading ->
                OnBoardingLoadingScreen(uiState = state)
        }
    }
}
