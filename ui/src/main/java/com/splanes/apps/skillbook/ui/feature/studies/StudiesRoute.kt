package com.splanes.apps.skillbook.ui.feature.studies

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun StudiesRoute(viewModel: StudiesViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    StudiesRoute(uiState = uiState)
}

@Composable
fun StudiesRoute(uiState: StudiesUiState) {
    Crossfade(targetState = uiState, label = "StudiesRoute") { state ->
        when (state) {
            is StudiesUiState.Studies -> StudiesScreen(state)
        }
    }
}
