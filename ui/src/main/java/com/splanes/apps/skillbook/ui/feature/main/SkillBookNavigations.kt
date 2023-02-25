package com.splanes.apps.skillbook.ui.feature.main

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

object SkillBookDestinations {
    const val OnBoarding = "on-boarding"
    const val Dashboard = "dashboard"
}

class SkillBookNavActions(private val navController: NavHostController) {
    val navToOnBoarding: () -> Unit = {
        navController.navigate(SkillBookDestinations.OnBoarding) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    val navToDashboard: () -> Unit = {
        navController.navigate(SkillBookDestinations.Dashboard) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}
