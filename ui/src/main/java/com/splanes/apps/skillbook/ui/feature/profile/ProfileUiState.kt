package com.splanes.apps.skillbook.ui.feature.profile

import com.splanes.apps.skillbook.domain.common.error.KnownException
import com.splanes.apps.skillbook.ui.feature.profile.model.ProfileAboutMeVisuals
import com.splanes.apps.skillbook.ui.feature.profile.model.ProfileContactVisuals
import com.splanes.apps.skillbook.ui.feature.profile.model.ProfileCurrentStatusVisuals

sealed interface ProfileUiState {
    data class Profile(
        val isLoading: Boolean,
        val error: KnownException?,
        val aboutMe: ProfileAboutMeVisuals,
        val contact: ProfileContactVisuals,
        val currentStatus: ProfileCurrentStatusVisuals
    ) : ProfileUiState
}
