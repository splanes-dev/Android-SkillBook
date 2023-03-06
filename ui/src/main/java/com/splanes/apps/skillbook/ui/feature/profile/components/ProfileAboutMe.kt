package com.splanes.apps.skillbook.ui.feature.profile.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.splanes.apps.skillbook.ui.R
import com.splanes.apps.skillbook.ui.common.utils.UiPreview
import com.splanes.apps.skillbook.ui.common.utils.rememberStateOf
import com.splanes.apps.skillbook.ui.components.expandable.ExpandCollapseArrow
import com.splanes.apps.skillbook.ui.feature.profile.model.ProfileAboutMeVisuals
import com.splanes.apps.skillbook.ui.theme.SkillBookTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileAboutMe(
    visuals: ProfileAboutMeVisuals,
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
                AboutMeHeader(expanded)

                Spacer(modifier = Modifier.height(16.dp))

                AboutMeDescription(
                    description = visuals.description,
                    expanded = expanded
                )
            }

            ExpandCollapseArrow(modifier = Modifier.align(Alignment.TopEnd), expanded = expanded)
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun AboutMeHeader(expanded: Boolean) {
    Column {
        AnimatedContent(targetState = expanded, label = "about me anim") { isExpanded ->
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.profile_about_me),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                textAlign = if (isExpanded) TextAlign.Center else TextAlign.Start
            )
        }
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
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = stringResource(id = R.string.profile_about_me_subtitle),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
    }
}

@Composable
private fun AboutMeDescription(description: String, expanded: Boolean) {
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
            text = description,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            textAlign = TextAlign.Justify
        )
    }
}

@Composable
@UiPreview
private fun ProfileAboutMePreview() {
    SkillBookTheme {
        var expanded by rememberStateOf(false)
        ProfileAboutMe(
            visuals = ProfileAboutMeVisuals(
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do " +
                    "eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad " +
                    "minim veniam, quis nostrud exercitation ullamco laboris nisi ut " +
                    "aliquip ex ea commodo consequat. Duis aute irure dolor in. Excepteur " +
                    "sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt " +
                    "mollit anim id est laborum."
            ),
            expanded = expanded,
            onClick = { expanded = !expanded }
        )
    }
}
