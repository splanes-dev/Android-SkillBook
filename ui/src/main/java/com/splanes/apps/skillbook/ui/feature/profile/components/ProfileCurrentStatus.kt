package com.splanes.apps.skillbook.ui.feature.profile.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.splanes.apps.skillbook.ui.R
import com.splanes.apps.skillbook.ui.common.utils.UiPreview
import com.splanes.apps.skillbook.ui.common.utils.rememberStateOf
import com.splanes.apps.skillbook.ui.components.expandable.ExpandCollapseArrow
import com.splanes.apps.skillbook.ui.feature.profile.model.ProfileCurrentStatusVisuals
import com.splanes.apps.skillbook.ui.feature.workhistory.model.WorkSearchStateVisuals
import com.splanes.apps.skillbook.ui.feature.workhistory.model.WorkVisuals
import com.splanes.apps.skillbook.ui.theme.SkillBookTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileCurrentStatus(
    visuals: ProfileCurrentStatusVisuals,
    expanded: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val containerAlpha by animateFloatAsState(
        targetValue = if (expanded) .5f else .2f,
        label = "container alpha"
    )

    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = containerAlpha),
        onClick = onClick
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 24.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 36.dp)
            ) {
                WorkStatus(visuals.currentWork, expanded)

                Spacer(modifier = Modifier.height(24.dp))

                ProfileStatusBadge(visuals.workSearchState, expanded)
            }

            ExpandCollapseArrow(modifier = Modifier.align(Alignment.TopEnd), expanded = expanded)
        }
    }
}

@Composable
private fun ProfileStatusBadge(
    visuals: WorkSearchStateVisuals,
    expanded: Boolean
) {
    val cornerShapeSize by animateDpAsState(
        targetValue = if (expanded) 16.dp else 24.dp,
        label = "corner shape anim"
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        Surface(
            color = visuals.backgroundColor(),
            shape = RoundedCornerShape(cornerShapeSize)
        ) {
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                Text(
                    text = stringResource(id = visuals.name),
                    style = MaterialTheme.typography.titleMedium,
                    color = visuals.foregroundColor()
                )

                AnimatedVisibility(
                    visible = expanded,
                    enter = expandIn(tween(750)),
                    exit = shrinkOut(tween(750))
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 8.dp),
                        text = stringResource(id = visuals.description),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = .7f)
                    )
                }
            }
        }
    }
}

@Composable
private fun WorkStatus(
    visuals: WorkVisuals,
    expanded: Boolean
) {
    Column {
        Text(
            text = stringResource(
                id = if (visuals.endDate != null) {
                    R.string.profile_last_job
                } else {
                    R.string.profile_current_job
                }
            ),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = visuals.charge,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            visuals.iconUrl?.let { iconUrl ->
                AsyncImage(
                    modifier = Modifier.size(24.dp),
                    model = iconUrl,
                    contentDescription = visuals.enterprise
                )

                Spacer(modifier = Modifier.width(8.dp))
            }

            Text(
                text = visuals.enterprise,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }

        AnimatedVisibility(
            visible = expanded,
            enter = expandVertically(
                animationSpec = tween(durationMillis = 500),
                expandFrom = Alignment.Top
            ),
            exit = shrinkVertically(
                animationSpec = tween(durationMillis = 500),
                shrinkTowards = Alignment.Top
            )
        ) {
            Column {
                val end = visuals.endDate ?: stringResource(id = R.string.present)

                Text(
                    text = "${visuals.startDate} - $end",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = visuals.description,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    textAlign = TextAlign.Justify
                )
            }
        }
    }
}

@Composable
private fun WorkSearchStateVisuals.backgroundColor() = MaterialTheme.colorScheme.run {
    when (this@backgroundColor) {
        WorkSearchStateVisuals.CloseToChange -> errorContainer
        WorkSearchStateVisuals.OpenToChange -> tertiaryContainer
        WorkSearchStateVisuals.OpenToOffers -> secondaryContainer
    }
}

@Composable
private fun WorkSearchStateVisuals.foregroundColor() = MaterialTheme.colorScheme.run {
    when (this@foregroundColor) {
        WorkSearchStateVisuals.CloseToChange -> onErrorContainer
        WorkSearchStateVisuals.OpenToChange -> onTertiaryContainer
        WorkSearchStateVisuals.OpenToOffers -> onSecondaryContainer
    }
}

@Composable
@UiPreview
private fun ProfileCurrentStatusPreview() {
    SkillBookTheme {
        var expanded by rememberStateOf(false)
        ProfileCurrentStatus(
            visuals = ProfileCurrentStatusVisuals(
                workSearchState = WorkSearchStateVisuals.OpenToChange,
                currentWork = WorkVisuals(
                    enterprise = "Adevinta",
                    charge = "Mobile Engineer",
                    startDate = "08/11/22",
                    description = "Working on the Fotocasa app."
                )
            ),
            expanded = expanded,
            onClick = { expanded = !expanded }
        )
    }
}
