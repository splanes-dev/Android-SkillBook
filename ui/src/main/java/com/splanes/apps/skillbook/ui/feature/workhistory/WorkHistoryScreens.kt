package com.splanes.apps.skillbook.ui.feature.workhistory

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.splanes.apps.skillbook.ui.R
import com.splanes.apps.skillbook.ui.common.utils.UiPreview
import com.splanes.apps.skillbook.ui.components.loader.LoaderLayout
import com.splanes.apps.skillbook.ui.components.topbar.SkillBookTopBar
import com.splanes.apps.skillbook.ui.theme.SkillBookTheme

@Composable
fun WorkHistoryScreen(
    uiState: WorkHistoryUiState.WorkHistory
) {
    LoaderLayout(loading = uiState.isLoading) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.surface),
            topBar = {
                SkillBookTopBar(title = stringResource(id = R.string.work_history))
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

@Composable
@UiPreview
private fun WorkHistoryScreenPreview() {
    SkillBookTheme {
        WorkHistoryScreen(
            uiState = WorkHistoryUiState.WorkHistory(
                isLoading = false,
                error = null
            )
        )
    }
}
