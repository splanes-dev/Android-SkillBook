package com.splanes.apps.skillbook.ui.feature.studies.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.splanes.apps.skillbook.ui.common.utils.UiPreview
import com.splanes.apps.skillbook.ui.common.utils.rememberStateOf
import com.splanes.apps.skillbook.ui.feature.studies.model.StudyEntryVisuals
import com.splanes.apps.skillbook.ui.theme.SkillBookTheme

@Composable
fun StudiesHistory(
    history: List<StudyEntryVisuals>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        itemsIndexed(history) { index, study ->
            StudyEntry(
                visuals = study,
                isFirstItem = index == 0,
                isLastItem = index == history.lastIndex
            )
        }
    }
}

@Composable
fun StudyEntry(visuals: StudyEntryVisuals, isFirstItem: Boolean, isLastItem: Boolean) {
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
            modifier = Modifier
                .onSizeChanged { size ->
                    if (size.height != 0 && lineHeight == 50f) {
                        val screenPixelDensity = context.resources.displayMetrics.density
                        lineHeight = size.height.toFloat() / screenPixelDensity
                    }
                }
        ) {
            StudyInformation(visuals = visuals, isLastItem = isLastItem)
        }
    }
}

@Composable
private fun ColumnScope.StudyInformation(visuals: StudyEntryVisuals, isLastItem: Boolean) {
    val date = if (visuals.endDate == null) {
        visuals.startDate
    } else {
        "${visuals.startDate} - ${visuals.endDate}"
    }

    Text(
        text = visuals.studyName,
        style = MaterialTheme.typography.headlineMedium,
        color = MaterialTheme.colorScheme.onSurface
    )

    Text(
        text = visuals.school,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onSurface
    )

    Text(
        text = date,
        style = MaterialTheme.typography.labelMedium,
        color = MaterialTheme.colorScheme.onSurface
    )

    Spacer(modifier = Modifier.height(8.dp))

    Text(
        text = visuals.description,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurface
    )

    if (!isLastItem) {
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun HistoricLine(modifier: Modifier = Modifier, isFirstItem: Boolean) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isFirstItem) {
            Spacer(modifier = Modifier.height(8.dp))
        } else {
            Box(
                modifier = Modifier
                    .width(2.dp)
                    .height(8.dp)
                    .background(color = MaterialTheme.colorScheme.primary)
            )
        }
        Box(
            modifier = Modifier
                .size(24.dp)
                .border(
                    width = 4.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = CircleShape
                )
        )
        Box(
            modifier = Modifier
                .width(2.dp)
                .weight(1f)
                .background(color = MaterialTheme.colorScheme.primary)
        )
    }
}

@Composable
@UiPreview
private fun StudiesHistoryPreview() {
    SkillBookTheme {
        StudiesHistory(
            modifier = Modifier.fillMaxSize(),
            history = listOf(
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
    }
}
