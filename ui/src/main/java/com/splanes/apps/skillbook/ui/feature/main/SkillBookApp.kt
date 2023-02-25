package com.splanes.apps.skillbook.ui.feature.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController

@Composable
fun SkillBookApp() {
    val navController = rememberNavController()
    val navActions = remember(navController) { SkillBookNavActions(navController) }

    SkillBookNavGraph(
        modifier = Modifier.fillMaxSize(),
        navController = navController,
        navActions = navActions
    )
}
