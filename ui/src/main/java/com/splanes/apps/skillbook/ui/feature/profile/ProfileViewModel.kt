package com.splanes.apps.skillbook.ui.feature.profile

import com.splanes.apps.skillbook.domain.common.error.KnownException
import com.splanes.apps.skillbook.ui.common.base.BaseViewModel
import com.splanes.apps.skillbook.ui.feature.profile.model.ProfileAboutMeVisuals
import com.splanes.apps.skillbook.ui.feature.profile.model.ProfileContactVisuals
import com.splanes.apps.skillbook.ui.feature.profile.model.ProfileCurrentStatusVisuals
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map

@HiltViewModel
class ProfileViewModel @Inject constructor() : BaseViewModel() {

    private val viewModelState: MutableStateFlow<ViewModelState> =
        MutableStateFlow(ViewModelState())

    val uiState: StateFlow<ProfileUiState> = viewModelState
        .map { viewModelState -> viewModelState.uiState() }
        .eagerly(viewModelState.value.uiState())

    private data class ViewModelState(
        val isLoading: Boolean = true,
        val error: KnownException? = null,
        val aboutMeVisuals: ProfileAboutMeVisuals = ProfileAboutMeVisuals(),
        val contactVisuals: ProfileContactVisuals = ProfileContactVisuals(),
        val currentStatusVisuals: ProfileCurrentStatusVisuals = ProfileCurrentStatusVisuals()
    ) {
        fun uiState(): ProfileUiState =
            ProfileUiState.Profile(
                isLoading = isLoading,
                error = error,
                aboutMe = aboutMeVisuals,
                contact = contactVisuals,
                currentStatus = currentStatusVisuals
            )
    }
}
