package com.splanes.apps.skillbook.ui.feature.workhistory

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun WorkHistoryRoute(
    viewModel: WorkHistoryViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    WorkHistoryRoute(uiState = uiState)
}

@Composable
fun WorkHistoryRoute(
    uiState: WorkHistoryUiState
) {
    Crossfade(targetState = uiState, label = "WorkHistoryRoute") { state ->
        when (state) {
            is WorkHistoryUiState.WorkHistory -> WorkHistoryScreen(state)
        }
    }
}
