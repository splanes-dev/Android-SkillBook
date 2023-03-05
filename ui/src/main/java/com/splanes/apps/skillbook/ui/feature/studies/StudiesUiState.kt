package com.splanes.apps.skillbook.ui.feature.studies

import com.splanes.apps.skillbook.domain.common.error.KnownException

sealed interface StudiesUiState {

    data class Studies(
        val isLoading: Boolean,
        val error: KnownException? = null
    ) : StudiesUiState
}
