package com.splanes.apps.skillbook.ui.feature.workhistory

import com.splanes.apps.skillbook.domain.common.error.KnownException
import com.splanes.apps.skillbook.domain.feature.workhistory.usecase.GetWorkHistoryUseCase
import com.splanes.apps.skillbook.ui.common.base.BaseViewModel
import com.splanes.apps.skillbook.ui.feature.workhistory.model.WorkVisuals
import com.splanes.apps.skillbook.ui.feature.workhistory.model.mapper.WorkHistoryVisualsMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

@HiltViewModel
class WorkHistoryViewModel @Inject constructor(
    private val mapper: WorkHistoryVisualsMapper,
    private val getWorkHistory: GetWorkHistoryUseCase
) : BaseViewModel() {

    private val viewModelState: MutableStateFlow<ViewModelState> =
        MutableStateFlow(ViewModelState())

    val uiState: StateFlow<WorkHistoryUiState> = viewModelState
        .map { viewModelState -> viewModelState.uiState() }
        .eagerly(viewModelState.value.uiState())

    init {
        fetchWorkHistory()
    }

    private fun fetchWorkHistory() {
        viewModelState.update { state -> state.copy(isLoading = true) }
        launch {
            getWorkHistory(Unit)
                .onSuccess { history ->
                    viewModelState.update { state ->
                        state.copy(
                            isLoading = false,
                            workHistoryEntries = history.let(mapper::map)
                        )
                    }
                }
                .onError { error ->
                    viewModelState.update { state ->
                        state.copy(
                            isLoading = false,
                            error = error
                        )
                    }
                }
        }
    }

    private data class ViewModelState(
        val isLoading: Boolean = true,
        val error: KnownException? = null,
        val workHistoryEntries: List<WorkVisuals> = emptyList()
    ) {
        fun uiState(): WorkHistoryUiState =
            WorkHistoryUiState.WorkHistory(
                isLoading = isLoading,
                error = error,
                workHistoryEntries = workHistoryEntries
            )
    }
}
