package com.splanes.apps.skillbook.ui.common.utils

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ImageResource {
    data class Vector(val imageVector: ImageVector) : ImageResource()
    data class Assets(@DrawableRes val res: Int) : ImageResource()
}
