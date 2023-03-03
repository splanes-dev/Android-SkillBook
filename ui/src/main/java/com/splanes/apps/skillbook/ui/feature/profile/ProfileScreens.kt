package com.splanes.apps.skillbook.ui.feature.profile

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.splanes.apps.skillbook.ui.R
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
import com.splanes.apps.skillbook.ui.theme.SkillBookTheme

@Composable
fun ProfileScreen(
    uiState: ProfileUiState.Profile
) {
    val errorDialogState = rememberErrorDialogState()

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
                    .padding(innerPaddingValues)
            ) {
                Surface(
                    modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.CenterHorizontally),
                    shape = CircleShape
                ) {
                    Image(
                        modifier = Modifier.size(125.dp),
                        painter = painterResource(id = R.drawable.cv_photo),
                        contentDescription = stringResource(
                            id = R.string.cv_photo_content_description
                        ),
                        contentScale = ContentScale.Crop
                    )
                }

                ProfileContact(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    visuals = uiState.contact
                )

                ProfileCurrentStatus()

                ProfileAboutMe()
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
                aboutMe = ProfileAboutMeVisuals(),
                contact = ProfileContactVisuals(),
                currentStatus = ProfileCurrentStatusVisuals()
            )
        )
    }
}
