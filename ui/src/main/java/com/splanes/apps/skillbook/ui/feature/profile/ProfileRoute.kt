package com.splanes.apps.skillbook.ui.feature.profile

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ProfileRoute(
    viewModel: ProfileViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ProfileRoute(
        uiState = uiState
    )
}

@Composable
fun ProfileRoute(
    uiState: ProfileUiState
) {
    Crossfade(uiState) { state ->
        when (state) {
            is ProfileUiState.Profile -> TODO()
        }
    }
}
