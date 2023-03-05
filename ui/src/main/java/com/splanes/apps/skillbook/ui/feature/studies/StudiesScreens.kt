package com.splanes.apps.skillbook.ui.feature.studies

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.splanes.apps.skillbook.ui.R
import com.splanes.apps.skillbook.ui.components.loader.LoaderLayout
import com.splanes.apps.skillbook.ui.components.topbar.SkillBookTopBar

@Composable
fun StudiesScreen(uiState: StudiesUiState.Studies) {
    val studies = uiState.studies

    LoaderLayout(loading = uiState.isLoading) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                SkillBookTopBar(title = stringResource(id = R.string.studies))
            }
        ) { innerPaddings ->
            Column(
                modifier = Modifier
                    .padding(innerPaddings)
                    .padding(top = 24.dp)
            ) {
            }
        }
    }
}
