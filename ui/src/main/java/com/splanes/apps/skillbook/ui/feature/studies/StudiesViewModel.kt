package com.splanes.apps.skillbook.ui.feature.studies

import com.splanes.apps.skillbook.domain.common.error.KnownException
import com.splanes.apps.skillbook.domain.feature.studies.usecase.GetStudiesUseCase
import com.splanes.apps.skillbook.ui.common.base.BaseViewModel
import com.splanes.apps.skillbook.ui.feature.studies.model.StudyEntryVisuals
import com.splanes.apps.skillbook.ui.feature.studies.model.mapper.StudiesVisualsMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

@HiltViewModel
class StudiesViewModel @Inject constructor(
    private val mapper: StudiesVisualsMapper,
    private val getStudies: GetStudiesUseCase
) : BaseViewModel() {

    private val viewModelState: MutableStateFlow<ViewModelState> =
        MutableStateFlow(ViewModelState())

    val uiState: StateFlow<StudiesUiState> = viewModelState
        .map { viewModelState -> viewModelState.uiState() }
        .eagerly(viewModelState.value.uiState())

    init {
        fetchStudies()
    }

    private fun fetchStudies() {
        viewModelState.update { state -> state.copy(isLoading = true) }
        launch {
            getStudies(Unit)
                .onSuccess { studies ->
                    viewModelState.update { state ->
                        state.copy(
                            isLoading = false,
                            studies = studies.let(mapper::map)
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
        val studies: List<StudyEntryVisuals> = emptyList()
    ) {
        fun uiState(): StudiesUiState =
            StudiesUiState.Studies(
                isLoading = isLoading,
                error = error,
                studies = studies
            )
    }
}
