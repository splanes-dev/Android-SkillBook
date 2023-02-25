package com.splanes.apps.skillbook.ui.feature.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.splanes.apps.skillbook.ui.feature.onboarding.model.OnBoardingUiData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class OnBoardingViewModel @Inject constructor() : ViewModel() {

    private val viewModelState: MutableStateFlow<ViewModelState> =
        MutableStateFlow(ViewModelState())

    val uiState: StateFlow<OnBoardingUiState> = viewModelState
        .map { viewModelState -> viewModelState.uiState() }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.uiState()
        )
    // val uiSideEffect: StateFlow<OnBoardingUiSideEffect?> = MutableStateFlow(null)

    fun onFinishOnBoarding() {

    }

    data class ViewModelState(
        val isLoading: Boolean = true,
        val onBoardingData: List<OnBoardingUiData> = emptyList()
    ) {
        fun uiState(): OnBoardingUiState =
            when {
                isLoading -> OnBoardingUiState.Loading
                onBoardingData.isNotEmpty() -> OnBoardingUiState.DataLoaded(onBoardingData)
                else -> error("Inconsistent OnBoarding ViewModel state")
            }
    }
}
