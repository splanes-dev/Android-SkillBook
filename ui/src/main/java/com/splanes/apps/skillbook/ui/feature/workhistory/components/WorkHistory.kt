package com.splanes.apps.skillbook.ui.feature.workhistory.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.splanes.apps.skillbook.ui.R
import com.splanes.apps.skillbook.ui.common.utils.UiPreview
import com.splanes.apps.skillbook.ui.common.utils.rememberStateOf
import com.splanes.apps.skillbook.ui.components.historic.HistoricLine
import com.splanes.apps.skillbook.ui.feature.workhistory.model.WorkVisuals
import com.splanes.apps.skillbook.ui.theme.SkillBookTheme

@Composable
fun WorkHistory(
    history: List<WorkVisuals>,
    modifier: Modifier = Modifier
) {
    var indexSelected by rememberStateOf(value = 0)

    LazyColumn(modifier = modifier) {
        itemsIndexed(history) { index, work ->
            WorkEntry(
                visuals = work,
                isFirstItem = index == 0,
                isLastItem = index == history.lastIndex,
                expanded = indexSelected == index,
                onClick = { indexSelected = index }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkEntry(
    visuals: WorkVisuals,
    isFirstItem: Boolean,
    isLastItem: Boolean,
    expanded: Boolean,
    onClick: () -> Unit
) {
    val context = LocalContext.current
    var lineHeight: Float by rememberStateOf(50f)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        HistoricLine(
            modifier = Modifier.heightIn(min = lineHeight.dp),
            isFirstItem = isFirstItem
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.onSizeChanged { size ->
                if (size.height != 0) {
                    val screenPixelDensity = context.resources.displayMetrics.density
                    lineHeight = size.height.toFloat() / screenPixelDensity
                }
            }
        ) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.tertiaryContainer,
                shape = MaterialTheme.shapes.medium,
                onClick = onClick
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    WorkInformation(visuals = visuals, expanded = expanded)
                }
            }

            if (!isLastItem) {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun WorkInformation(
    visuals: WorkVisuals,
    expanded: Boolean
) {
    val endDate = visuals.endDate ?: stringResource(id = R.string.present)

    Text(
        text = visuals.enterprise,
        style = MaterialTheme.typography.headlineMedium,
        color = MaterialTheme.colorScheme.onTertiaryContainer
    )

    Text(
        text = visuals.charge,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onTertiaryContainer
    )

    Text(
        text = "${visuals.startDate} - $endDate",
        style = MaterialTheme.typography.labelMedium,
        color = MaterialTheme.colorScheme.onTertiaryContainer
    )

    Spacer(modifier = Modifier.height(8.dp))

    AnimatedVisibility(
        visible = expanded,
        enter = expandVertically(
            animationSpec = tween(durationMillis = 750),
            expandFrom = Alignment.Top
        ),
        exit = shrinkVertically(
            animationSpec = tween(durationMillis = 750),
            shrinkTowards = Alignment.Top
        )
    ) {
        Text(
            text = visuals.description,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onTertiaryContainer
        )
    }
}

@Composable
@UiPreview
private fun WorkHistoryPreview() {
    SkillBookTheme {
        WorkHistory(
            history = listOf(
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
    }
}
