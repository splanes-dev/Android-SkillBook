package com.splanes.apps.skillbook.ui.components.error.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ErrorOutline
import androidx.compose.ui.graphics.vector.ImageVector

data class ErrorDialogVisuals(
    val icon: ImageVector = Icons.Rounded.ErrorOutline,
    val title: String,
    val description: String,
    val closeButton: String
)
