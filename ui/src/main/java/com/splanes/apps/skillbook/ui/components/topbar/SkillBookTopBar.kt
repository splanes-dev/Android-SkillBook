package com.splanes.apps.skillbook.ui.components.topbar

import android.app.Activity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material.icons.rounded.LightMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.splanes.apps.skillbook.ui.common.utils.UiPreview
import com.splanes.apps.skillbook.ui.theme.SkillBookTheme
import com.splanes.apps.skillbook.ui.theme.isDarkMode

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SkillBookTopBar(
    title: String,
    modifier: Modifier = Modifier,
    navigation: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {}
) {
    val uiController = rememberSystemUiController()
    uiController.setStatusBarColor(color = MaterialTheme.colorScheme.surface)

    TopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        navigationIcon = navigation,
        actions = {
            actions()

            Spacer(modifier = Modifier.width(8.dp))

            NightModeSwitcherIcon()
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
            titleContentColor = MaterialTheme.colorScheme.onSurface,
            navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
            actionIconContentColor = MaterialTheme.colorScheme.onSurface
        )
    )
}

@Composable
private fun NightModeSwitcherIcon() {
    val context = LocalContext.current
    val isDarkMode = isDarkMode()

    IconButton(onClick = {
        AppCompatDelegate.setDefaultNightMode(
            if (isDarkMode) {
                AppCompatDelegate.MODE_NIGHT_NO
            } else {
                AppCompatDelegate.MODE_NIGHT_YES
            }
        )
        (context as? Activity)?.recreate()
    }) {
        Icon(
            imageVector = Icons.Rounded.run {
                if (isDarkMode) {
                    LightMode
                } else {
                    DarkMode
                }
            },
            contentDescription = null
        )
    }
}

@Composable
@UiPreview
private fun SkillBookTopBarPreview() {
    SkillBookTheme {
        SkillBookTopBar(title = "Profile")
    }
}
