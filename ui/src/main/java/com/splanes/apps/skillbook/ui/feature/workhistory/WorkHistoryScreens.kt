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
import com.splanes.apps.skillbook.ui.feature.workhistory.components.WorkHistory
import com.splanes.apps.skillbook.ui.feature.workhistory.model.WorkVisuals
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
                WorkHistory(
                    modifier = Modifier.padding(16.dp),
                    history = uiState.workHistoryEntries
                )
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
                error = null,
                workHistoryEntries = listOf(
                    WorkVisuals(
                        iconUrl = null,
                        enterprise = "Adevinta",
                        charge = "Mobile Engineer",
                        description = "Role of checkout squad head at Bershka App. I was involved" +
                            " in a refactor from Java legacy App to Kotlin.\nAs squad leader, " +
                            "I was in charge of developing new shipping and payment methods," +
                            "cart management, and some profile settings as billing addresses as " +
                            "well as team management or sprint organization.",
                        startDate = "November 2021",
                        endDate = null
                    ),
                    WorkVisuals(
                        iconUrl = null,
                        enterprise = "KubikData",
                        charge = "Senior Android Developer",
                        description = "Lead of KubikData's Android App among my duties I realize " +
                            "all new features implementation, CI/CD management, Sprint " +
                            "planning, Google Play publication.",
                        startDate = "June 2021",
                        endDate = "November 2021"
                    ),
                    WorkVisuals(
                        iconUrl = null,
                        enterprise = "BeMobile",
                        charge = "Mid/Senior Android Developer",
                        description = "Role of checkout squad head at Bershka App. I was involved" +
                            " in a refactor from Java legacy App to Kotlin.\nAs squad leader, " +
                            "I was in charge of developing new shipping and payment methods," +
                            "cart management, and some profile settings as billing addresses as " +
                            "well as team management or sprint organization.",
                        startDate = "February 2020",
                        endDate = "June 2021"
                    )
                )
            )
        )
    }
}
