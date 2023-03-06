package com.splanes.apps.skillbook.ui.feature.workhistory

import com.splanes.apps.skillbook.domain.common.error.KnownException
import com.splanes.apps.skillbook.ui.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map

@HiltViewModel
class WorkHistoryViewModel @Inject constructor() : BaseViewModel() {

    private val viewModelState: MutableStateFlow<ViewModelState> =
        MutableStateFlow(ViewModelState())

    val uiState: StateFlow<WorkHistoryUiState> = viewModelState
        .map { viewModelState -> viewModelState.uiState() }
        .eagerly(viewModelState.value.uiState())

    private data class ViewModelState(
        val isLoading: Boolean = true,
        val error: KnownException? = null
    ) {
        fun uiState(): WorkHistoryUiState =
            WorkHistoryUiState.WorkHistory(
                isLoading = isLoading,
                error = error
            )
    }
}
