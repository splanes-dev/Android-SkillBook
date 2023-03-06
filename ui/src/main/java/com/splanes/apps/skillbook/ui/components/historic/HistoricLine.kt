package com.splanes.apps.skillbook.ui.components.historic

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HistoricLine(modifier: Modifier = Modifier, isFirstItem: Boolean) {
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
