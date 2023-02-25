package com.splanes.apps.skillbook.ui.theme.colors

import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.graphics.Color

private val colorDarkPrimary = Color(0xFF57DBC4)
private val colorDarkOnPrimary = Color(0xFF00382F)
private val colorDarkPrimaryContainer = Color(0xFF005045)
private val colorDarkOnPrimaryContainer = Color(0xFF77F8DF)
private val colorDarkSecondary = Color(0xFFB1CCC5)
private val colorDarkOnSecondary = Color(0xFF1C3530)
private val colorDarkSecondaryContainer = Color(0xFF334B46)
private val colorDarkOnSecondaryContainer = Color(0xFFCDE8E0)
private val colorDarkTertiary = Color(0xFFABCAE4)
private val colorDarkOnTertiary = Color(0xFF123348)
private val colorDarkTertiaryContainer = Color(0xFF2B4A60)
private val colorDarkOnTertiaryContainer = Color(0xFFC9E6FF)
private val colorDarkError = Color(0xFFFFB4AB)
private val colorDarkErrorContainer = Color(0xFF93000A)
private val colorDarkOnError = Color(0xFF690005)
private val colorDarkOnErrorContainer = Color(0xFFFFDAD6)
private val colorDarkBackground = Color(0xFF191C1B)
private val colorDarkOnBackground = Color(0xFFE0E3E1)
private val colorDarkSurface = Color(0xFF191C1B)
private val colorDarkOnSurface = Color(0xFFE0E3E1)
private val colorDarkSurfaceVariant = Color(0xFF3F4946)
private val colorDarkOnSurfaceVariant = Color(0xFFBEC9C5)
private val colorDarkOutline = Color(0xFF89938F)
private val colorDarkInverseOnSurface = Color(0xFF191C1B)
private val colorDarkInverseSurface = Color(0xFFE0E3E1)
private val colorDarkInversePrimary = Color(0xFF006B5D)
private val colorDarkSurfaceTint = Color(0xFF57DBC4)
private val colorDarkOutlineVariant = Color(0xFF3F4946)
private val colorDarkScrim = Color(0xFF000000)

internal val DarkColorScheme by lazy {
    darkColorScheme(
        primary = colorDarkPrimary,
        onPrimary = colorDarkOnPrimary,
        primaryContainer = colorDarkPrimaryContainer,
        onPrimaryContainer = colorDarkOnPrimaryContainer,
        secondary = colorDarkSecondary,
        onSecondary = colorDarkOnSecondary,
        secondaryContainer = colorDarkSecondaryContainer,
        onSecondaryContainer = colorDarkOnSecondaryContainer,
        tertiary = colorDarkTertiary,
        onTertiary = colorDarkOnTertiary,
        tertiaryContainer = colorDarkTertiaryContainer,
        onTertiaryContainer = colorDarkOnTertiaryContainer,
        background = colorDarkBackground,
        onBackground = colorDarkOnBackground,
        surface = colorDarkSurface,
        onSurface = colorDarkOnSurface,
        surfaceTint = colorDarkSurfaceTint,
        surfaceVariant = colorDarkSurfaceVariant,
        onSurfaceVariant = colorDarkOnSurfaceVariant,
        error = colorDarkError,
        onError = colorDarkOnError,
        errorContainer = colorDarkErrorContainer,
        onErrorContainer = colorDarkOnErrorContainer,
        outline = colorDarkOutline,
        outlineVariant = colorDarkOutlineVariant,
        scrim = colorDarkScrim,
        inversePrimary = colorDarkInversePrimary,
        inverseSurface = colorDarkInverseSurface,
        inverseOnSurface = colorDarkInverseOnSurface
    )
}
