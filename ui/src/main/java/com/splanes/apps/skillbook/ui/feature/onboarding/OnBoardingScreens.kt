package com.splanes.apps.skillbook.ui.feature.onboarding

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun OnBoardingLoadingScreen() {
    Box(modifier = Modifier.fillMaxSize()) {

    }
}

@Composable
fun OnBoardingPagerScreen(
    uiState: OnBoardingUiState.DataLoaded,
    onFinishOnBoarding: () -> Unit
) {
}
