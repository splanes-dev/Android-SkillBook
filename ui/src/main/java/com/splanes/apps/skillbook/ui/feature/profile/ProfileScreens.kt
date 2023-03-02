package com.splanes.apps.skillbook.ui.feature.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.splanes.apps.skillbook.ui.R
import com.splanes.apps.skillbook.ui.components.topbar.SkillBookTopBar

@Composable
fun ProfileScreen(
    uiState: ProfileUiState.Profile
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            SkillBookTopBar(title = stringResource(id = R.string.profile))
        }
    ) { innerPaddingValues ->
        Column(modifier = Modifier.padding(innerPaddingValues)) {
        }
    }
}
