package com.splanes.apps.skillbook.ui.feature.workhistory

import com.splanes.apps.skillbook.domain.common.error.KnownException
import com.splanes.apps.skillbook.ui.feature.workhistory.model.WorkVisuals

sealed interface WorkHistoryUiState {

    data class WorkHistory(
        val isLoading: Boolean,
        val error: KnownException?,
        val workHistoryEntries: List<WorkVisuals>
    ) : WorkHistoryUiState
}
