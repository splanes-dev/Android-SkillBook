package com.splanes.apps.skillbook.ui.feature.dashboard.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Diversity3
import androidx.compose.material.icons.rounded.IntegrationInstructions
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.School
import androidx.compose.material.icons.rounded.WorkHistory
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.splanes.apps.skillbook.ui.R
import com.splanes.apps.skillbook.ui.feature.profile.ProfileRoute
import com.splanes.apps.skillbook.ui.feature.profile.ProfileViewModel
import com.splanes.apps.skillbook.ui.feature.studies.StudiesRoute
import com.splanes.apps.skillbook.ui.feature.studies.StudiesViewModel
import com.splanes.apps.skillbook.ui.feature.workhistory.WorkHistoryRoute
import com.splanes.apps.skillbook.ui.feature.workhistory.WorkHistoryViewModel

@Composable
fun DashboardNavGraph(
    navController: NavHostController,
    navActions: DashboardNavigationActions,
    current: NavBackStackEntry?,
    modifier: Modifier = Modifier,
    startDestination: String = DashboardDestinations.Profile
) {
    Column(modifier = Modifier.fillMaxSize()) {
        NavHost(
            modifier = modifier.weight(1f),
            navController = navController,
            startDestination = startDestination
        ) {
            composable(DashboardDestinations.Studies) {
                val viewModel: StudiesViewModel = hiltViewModel()
                StudiesRoute(viewModel)
            }
            composable(DashboardDestinations.Jobs) {
                val viewModel: WorkHistoryViewModel = hiltViewModel()
                WorkHistoryRoute(viewModel)
            }
            composable(DashboardDestinations.Profile) {
                val viewModel: ProfileViewModel = hiltViewModel()
                ProfileRoute(viewModel)
            }
            composable(DashboardDestinations.HardSkills) {
            }
            composable(DashboardDestinations.SoftSkills) {
            }
        }
        BottomNavigation(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ) {
            DashboardDestinations.all.forEach { destination ->

                val label = when (destination) {
                    DashboardDestinations.Profile -> R.string.profile
                    DashboardDestinations.HardSkills -> R.string.hard_skills
                    DashboardDestinations.SoftSkills -> R.string.soft_skills
                    DashboardDestinations.Jobs -> R.string.work_history
                    DashboardDestinations.Studies -> R.string.studies
                    else -> error("Destination not registered")
                }.let { id -> stringResource(id) }

                BottomNavigationItem(
                    selected = current.isSelected(destination),
                    onClick = when (destination) {
                        DashboardDestinations.Profile -> navActions.navToProfile
                        DashboardDestinations.HardSkills -> navActions.navToHardSkills
                        DashboardDestinations.SoftSkills -> navActions.navToSoftSkills
                        DashboardDestinations.Jobs -> navActions.navToJobs
                        DashboardDestinations.Studies -> navActions.navToStudies
                        else -> error("Destination not registered")
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Rounded.run {
                                when (destination) {
                                    DashboardDestinations.Profile -> Person
                                    DashboardDestinations.HardSkills -> IntegrationInstructions
                                    DashboardDestinations.SoftSkills -> Diversity3
                                    DashboardDestinations.Jobs -> WorkHistory
                                    DashboardDestinations.Studies -> School
                                    else -> error("Destination not registered")
                                }
                            },
                            contentDescription = label,
                            tint = MaterialTheme.colorScheme.run {
                                if (current.isSelected(destination)) {
                                    onPrimaryContainer
                                } else {
                                    onPrimaryContainer.copy(alpha = .3f)
                                }
                            }
                        )
                    },
                    label = {
                        Text(
                            text = label,
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Medium
                            ),
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            textAlign = TextAlign.Center,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    alwaysShowLabel = false,
                    selectedContentColor = MaterialTheme.colorScheme.onPrimary,
                    unselectedContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = .3f)
                )
            }
        }
    }
}

private fun NavBackStackEntry?.isSelected(route: String) =
    this?.destination?.route == route
