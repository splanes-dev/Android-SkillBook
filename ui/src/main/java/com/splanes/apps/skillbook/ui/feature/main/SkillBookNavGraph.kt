package com.splanes.apps.skillbook.ui.feature.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.splanes.apps.skillbook.ui.feature.dashboard.DashboardRoute
import com.splanes.apps.skillbook.ui.feature.onboarding.OnBoardingRoute
import com.splanes.apps.skillbook.ui.feature.onboarding.OnBoardingViewModel

@Composable
fun SkillBookNavGraph(
    navController: NavHostController,
    navActions: SkillBookNavActions,
    modifier: Modifier = Modifier,
    startDestination: String = SkillBookDestinations.OnBoarding
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(SkillBookDestinations.OnBoarding) {
            val viewModel: OnBoardingViewModel = hiltViewModel()
            OnBoardingRoute(
                viewModel = viewModel,
                onNavToDashboard = navActions.navToDashboard
            )
        }
        composable(SkillBookDestinations.Dashboard) {
            DashboardRoute()
        }
    }
}
