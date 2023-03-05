package com.splanes.apps.skillbook.ui.feature.studies

import com.splanes.apps.skillbook.domain.common.error.KnownException
import com.splanes.apps.skillbook.ui.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map

@HiltViewModel
class StudiesViewModel @Inject constructor() : BaseViewModel() {

    private val viewModelState: MutableStateFlow<ViewModelState> =
        MutableStateFlow(ViewModelState())

    val uiState: StateFlow<StudiesUiState> = viewModelState
        .map { viewModelState -> viewModelState.uiState() }
        .eagerly(viewModelState.value.uiState())

    private data class ViewModelState(
        val isLoading: Boolean = true,
        val error: KnownException? = null
    ) {
        fun uiState(): StudiesUiState =
            StudiesUiState.Studies(
                isLoading = isLoading,
                error = error
            )
    }
}
