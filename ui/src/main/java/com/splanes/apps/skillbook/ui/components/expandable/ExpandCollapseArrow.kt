package com.splanes.apps.skillbook.ui.components.expandable

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ExpandLess
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import com.splanes.apps.skillbook.ui.R

@Composable
fun ExpandCollapseArrow(modifier: Modifier = Modifier, expanded: Boolean) {
    val rotation by animateFloatAsState(
        targetValue = if (expanded) 0f else 180f,
        label = "icon rotation"
    )

    Icon(
        modifier = modifier
            .rotate(rotation),
        imageVector = Icons.Rounded.ExpandLess,
        contentDescription = stringResource(
            id = if (expanded) {
                R.string.expanded_description
            } else {
                R.string.collapsed_description
            }
        ),
        tint = MaterialTheme.colorScheme.onPrimaryContainer
    )
}
