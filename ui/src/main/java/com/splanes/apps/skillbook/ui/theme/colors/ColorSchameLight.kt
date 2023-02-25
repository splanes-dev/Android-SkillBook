package com.splanes.apps.skillbook.ui.theme.colors

import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val colorLightPrimary = Color(0xFF006B5D)
val colorLightOnPrimary = Color(0xFFFFFFFF)
val colorLightPrimaryContainer = Color(0xFF77F8DF)
val colorLightOnPrimaryContainer = Color(0xFF00201B)
val colorLightSecondary = Color(0xFF4A635D)
val colorLightOnSecondary = Color(0xFFFFFFFF)
val colorLightSecondaryContainer = Color(0xFFCDE8E0)
val colorLightOnSecondaryContainer = Color(0xFF06201B)
val colorLightTertiary = Color(0xFF446278)
val colorLightOnTertiary = Color(0xFFFFFFFF)
val colorLightTertiaryContainer = Color(0xFFC9E6FF)
val colorLightOnTertiaryContainer = Color(0xFF001E2F)
val colorLightError = Color(0xFFBA1A1A)
val colorLightErrorContainer = Color(0xFFFFDAD6)
val colorLightOnError = Color(0xFFFFFFFF)
val colorLightOnErrorContainer = Color(0xFF410002)
val colorLightBackground = Color(0xFFFAFDFA)
val colorLightOnBackground = Color(0xFF191C1B)
val colorLightSurface = Color(0xFFFAFDFA)
val colorLightOnSurface = Color(0xFF191C1B)
val colorLightSurfaceVariant = Color(0xFFDAE5E1)
val colorLightOnSurfaceVariant = Color(0xFF3F4946)
val colorLightOutline = Color(0xFF6F7976)
val colorLightInverseOnSurface = Color(0xFFEFF1EF)
val colorLightInverseSurface = Color(0xFF2D3130)
val colorLightInversePrimary = Color(0xFF57DBC4)
val colorLightSurfaceTint = Color(0xFF006B5D)
val colorLightOutlineVariant = Color(0xFFBEC9C5)
val colorLightScrim = Color(0xFF000000)

internal val LightColorScheme by lazy {
    lightColorScheme(
        primary = colorLightPrimary,
        onPrimary = colorLightOnPrimary,
        primaryContainer = colorLightPrimaryContainer,
        onPrimaryContainer = colorLightOnPrimaryContainer,
        secondary = colorLightSecondary,
        onSecondary = colorLightOnSecondary,
        secondaryContainer = colorLightSecondaryContainer,
        onSecondaryContainer = colorLightOnSecondaryContainer,
        tertiary = colorLightTertiary,
        onTertiary = colorLightOnTertiary,
        tertiaryContainer = colorLightTertiaryContainer,
        onTertiaryContainer = colorLightOnTertiaryContainer,
        background = colorLightBackground,
        onBackground = colorLightOnBackground,
        surface = colorLightSurface,
        onSurface = colorLightOnSurface,
        surfaceTint = colorLightSurfaceTint,
        surfaceVariant = colorLightSurfaceVariant,
        onSurfaceVariant = colorLightOnSurfaceVariant,
        error = colorLightError,
        onError = colorLightOnError,
        errorContainer = colorLightErrorContainer,
        onErrorContainer = colorLightOnErrorContainer,
        outline = colorLightOutline,
        outlineVariant = colorLightOutlineVariant,
        scrim = colorLightScrim,
        inversePrimary = colorLightInversePrimary,
        inverseSurface = colorLightInverseSurface,
        inverseOnSurface = colorLightInverseOnSurface
    )
}
