package com.splanes.apps.skillbook.ui.feature.onboarding

import com.splanes.apps.skillbook.domain.common.error.KnownException
import com.splanes.apps.skillbook.domain.feature.onboarding.usecase.IsOnBoardingVisibleUseCase
import com.splanes.apps.skillbook.ui.common.base.BaseViewModel
import com.splanes.apps.skillbook.ui.feature.onboarding.model.OnBoardingPageVisuals
import com.splanes.apps.skillbook.ui.feature.onboarding.model.OnBoardingUiPages
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    val isOnBoardingVisibleUseCase: IsOnBoardingVisibleUseCase
) : BaseViewModel() {

    private val viewModelState: MutableStateFlow<ViewModelState> =
        MutableStateFlow(ViewModelState())

    val uiState: StateFlow<OnBoardingUiState> = viewModelState
        .map { viewModelState -> viewModelState.uiState() }
        .eagerly(viewModelState.value.uiState())

    private val _uiSideEffect: MutableStateFlow<OnBoardingUiSideEffect?> =
        MutableStateFlow(null)

    val uiSideEffect: StateFlow<OnBoardingUiSideEffect?> = _uiSideEffect
        .eagerly(null)

    init {
        isOnBoardingVisible()
    }

    private fun isOnBoardingVisible() {
        viewModelState.update { state -> state.copy(isLoading = true) }
        launch {
            isOnBoardingVisibleUseCase(Unit)
                .onSuccess { isVisible ->
                    if (isVisible) {
                        viewModelState.update { state ->
                            state.copy(
                                isLoading = false,
                                onBoardingData = OnBoardingUiPages
                            )
                        }
                    } else {
                        _uiSideEffect.update { OnBoardingUiSideEffect.NavToDashboard }
                    }
                }
                .onError { err ->
                    viewModelState.update { state ->
                        state.copy(
                            isLoading = false,
                            error = err
                        )
                    }
                }
        }
    }

    fun onFinishOnBoarding() {
    }

    data class ViewModelState(
        val isLoading: Boolean = true,
        val error: KnownException? = null,
        val onBoardingData: List<OnBoardingPageVisuals> = emptyList()
    ) {
        fun uiState(): OnBoardingUiState =
            when {
                isLoading ->
                    OnBoardingUiState.Loading(error = error)

                onBoardingData.isNotEmpty() ->
                    OnBoardingUiState.DataLoaded(
                        error = error,
                        pages = onBoardingData
                    )

                else -> error("Inconsistent OnBoarding ViewModel state")
            }
    }
}
