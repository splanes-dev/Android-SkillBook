package com.splanes.apps.skillbook.ui.feature.profile

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.splanes.apps.skillbook.ui.R
import com.splanes.apps.skillbook.ui.common.utils.rememberStateOf
import com.splanes.apps.skillbook.ui.components.error.ErrorDialog
import com.splanes.apps.skillbook.ui.components.error.rememberErrorDialogState
import com.splanes.apps.skillbook.ui.components.loader.LoaderLayout
import com.splanes.apps.skillbook.ui.components.topbar.SkillBookTopBar
import com.splanes.apps.skillbook.ui.feature.profile.components.ProfileAboutMe
import com.splanes.apps.skillbook.ui.feature.profile.components.ProfileContact
import com.splanes.apps.skillbook.ui.feature.profile.components.ProfileCurrentStatus
import com.splanes.apps.skillbook.ui.feature.profile.model.ProfileAboutMeVisuals
import com.splanes.apps.skillbook.ui.feature.profile.model.ProfileContactVisuals
import com.splanes.apps.skillbook.ui.feature.profile.model.ProfileCurrentStatusVisuals
import com.splanes.apps.skillbook.ui.feature.profile.model.ProfileSection
import com.splanes.apps.skillbook.ui.feature.workhistory.model.WorkSearchStateVisuals
import com.splanes.apps.skillbook.ui.feature.workhistory.model.WorkVisuals
import com.splanes.apps.skillbook.ui.theme.SkillBookTheme

@Composable
fun ProfileScreen(
    uiState: ProfileUiState.Profile
) {
    val scrollState = rememberScrollState()
    val errorDialogState = rememberErrorDialogState()
    var selectedSection: ProfileSection? by rememberStateOf(value = ProfileSection.Contact)
    val onSectionClick: (ProfileSection) -> Unit = { section ->
        selectedSection = if (selectedSection == section) {
            null
        } else {
            section
        }
    }

    LaunchedEffect(uiState.error) {
        if (uiState.error != null) {
			/*errorDialogState.show(
				ErrorDialogVisuals(
					title = , description = , closeButton =
				)
			)*/
        }
    }

    LoaderLayout(loading = uiState.isLoading) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                SkillBookTopBar(title = stringResource(id = R.string.profile))
            }
        ) { innerPaddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(innerPaddingValues)
                    .padding(top = 16.dp)
            ) {
                ProfileContact(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    visuals = uiState.contact,
                    expanded = selectedSection == ProfileSection.Contact,
                    onClick = { onSectionClick(ProfileSection.Contact) }
                )

                Spacer(modifier = Modifier.height(16.dp))

                ProfileCurrentStatus(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    visuals = uiState.currentStatus,
                    expanded = selectedSection == ProfileSection.CurrentStatus,
                    onClick = { onSectionClick(ProfileSection.CurrentStatus) }
                )

                Spacer(modifier = Modifier.height(16.dp))

                ProfileAboutMe(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    visuals = uiState.aboutMe,
                    expanded = selectedSection == ProfileSection.AboutMe,
                    onClick = { onSectionClick(ProfileSection.AboutMe) }
                )
            }
        }
    }

    ErrorDialog(state = errorDialogState)
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, device = Devices.PIXEL_4)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, device = Devices.PIXEL_4)
private fun ProfileScreenPreview() {
    SkillBookTheme {
        ProfileScreen(
            uiState =
            ProfileUiState.Profile(
                isLoading = false,
                error = null,
                aboutMe = ProfileAboutMeVisuals(
                    description =
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do " +
                        "eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad " +
                        "minim veniam, quis nostrud exercitation ullamco laboris nisi ut " +
                        "aliquip ex ea commodo consequat. Duis aute irure dolor in. Excepteur " +
                        "sint occaecat cupidatat non proident, sunt in culpa qui officia " +
                        "deserunt mollit anim id est laborum."
                ),
                contact = ProfileContactVisuals(
                    name = "Sergi Planes",
                    location = "Barcelona, Spain",
                    locationLatLng = "41.4065534,2.166795,13z",
                    email = "splanes@protonmail.com",
                    phone = "628131871",
                    linkedInUrl = "https://www.linkedin.com/in/sergi-planes-tor-8318a998/",
                    githubUrl = "https://www.github.com/splanes-dev"
                ),
                currentStatus = ProfileCurrentStatusVisuals(
                    workSearchState = WorkSearchStateVisuals.OpenToChange,
                    currentWork = WorkVisuals(
                        enterprise = "Adevinta",
                        charge = "Mobile Engineer",
                        startDate = "08/11/22",
                        description = "Working on the Fotocasa app."
                    )
                )
            )
        )
    }
}
