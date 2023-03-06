package com.splanes.apps.skillbook.ui.feature.studies

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
import com.splanes.apps.skillbook.ui.feature.studies.components.StudiesHistory
import com.splanes.apps.skillbook.ui.feature.studies.model.StudyEntryVisuals
import com.splanes.apps.skillbook.ui.theme.SkillBookTheme

@Composable
fun StudiesScreen(uiState: StudiesUiState.Studies) {
    LoaderLayout(loading = uiState.isLoading) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.surface),
            topBar = {
                SkillBookTopBar(title = stringResource(id = R.string.studies))
            }
        ) { innerPaddings ->
            Column(
                modifier = Modifier
                    .padding(innerPaddings)
                    .padding(top = 24.dp)
            ) {
                StudiesHistory(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    history = uiState.studies
                )
            }
        }
    }
}

@Composable
@UiPreview
private fun StudiesScreenPreview() {
    SkillBookTheme {
        StudiesScreen(
            uiState = StudiesUiState.Studies(
                isLoading = false,
                error = null,
                studies = listOf(
                    StudyEntryVisuals(
                        studyName = "CFGS DAM",
                        school = "Escola Jesuïtes El Clot",
                        description = "Prize for the best final project of the promotion.",
                        startDate = "September 2015",
                        endDate = "July 2017"
                    ),
                    StudyEntryVisuals(
                        studyName = "Computer Science",
                        school = "UOC - Universistat Oberta de Catalunya",
                        description = "Currently studying. Studies previously initiated at Ramón " +
                            "Llull University \"La Salle\".",
                        startDate = "Present",
                        endDate = null
                    )
                )
            )
        )
    }
}
