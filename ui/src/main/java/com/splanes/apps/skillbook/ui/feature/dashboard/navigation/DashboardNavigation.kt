package com.splanes.apps.skillbook.ui.feature.dashboard.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

object DashboardDestinations {
    val all: List<String>
        get() = listOf(
            Studies,
            Jobs,
            Profile,
            SoftSkills,
            HardSkills
        )
    const val Studies = "dashboard/studies"
    const val Jobs = "dashboard/jobs"
    const val SoftSkills = "dashboard/soft-skills"
    const val HardSkills = "dashboard/hard-skills"
    const val Profile = "dashboard/profile"
}

class DashboardNavigationActions(private val navController: NavHostController) {
    val navToProfile: () -> Unit = {
        navController.navigate(DashboardDestinations.Profile) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            restoreState = true
        }
    }
    val navToStudies: () -> Unit = {
        navController.navigate(DashboardDestinations.Studies) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            restoreState = true
        }
    }
    val navToJobs: () -> Unit = {
        navController.navigate(DashboardDestinations.Jobs) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            restoreState = true
        }
    }
    val navToSoftSkills: () -> Unit = {
        navController.navigate(DashboardDestinations.SoftSkills) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            restoreState = true
        }
    }
    val navToHardSkills: () -> Unit = {
        navController.navigate(DashboardDestinations.HardSkills) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            restoreState = true
        }
    }
}
