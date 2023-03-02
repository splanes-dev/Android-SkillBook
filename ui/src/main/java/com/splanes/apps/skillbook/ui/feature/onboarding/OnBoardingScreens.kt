package com.splanes.apps.skillbook.ui.feature.onboarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.splanes.apps.skillbook.ui.R
import com.splanes.apps.skillbook.ui.feature.onboarding.components.OnBoardingPage
import com.splanes.apps.skillbook.ui.feature.onboarding.model.OnBoardingUiPages
import com.splanes.apps.skillbook.ui.theme.SkillBookTheme

@Composable
fun OnBoardingLoadingScreen(
    uiState: OnBoardingUiState.Loading
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Crossfade(targetState = uiState.error, label = "OnBoardingLoadingScreen") { err ->
            if (err != null) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.oops),
                        style = MaterialTheme.typography.headlineMedium
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Text(
                        text = stringResource(id = R.string.generic_error_description),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            } else {
                CircularProgressIndicator()
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingPagerScreen(
    uiState: OnBoardingUiState.DataLoaded,
    onFinishOnBoarding: () -> Unit
) {
    val pages = uiState.pages
    val pageState = rememberPagerState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            modifier = Modifier.weight(1f),
            state = pageState,
            count = pages.count()
        ) { index ->

            Column {
                OnBoardingPage(
                    modifier = Modifier.weight(1f),
                    visible = pageState.currentPage == index,
                    visuals = pages[index]
                )

                AnimatedVisibility(
                    visible = index == pages.lastIndex,
                    enter = expandVertically(animationSpec = tween(durationMillis = 500)),
                    exit = shrinkVertically(animationSpec = tween(durationMillis = 500))
                ) {
                    Spacer(modifier = Modifier.weight(1f))

                    Button(onClick = onFinishOnBoarding) {
                        Text(
                            text = stringResource(id = R.string.start),
                            style = MaterialTheme.typography.titleSmall
                        )
                    }
                }
            }
        }

        HorizontalPagerIndicator(
            modifier = Modifier.wrapContentSize(),
            pagerState = pageState,
            activeColor = MaterialTheme.colorScheme.primary,
            inactiveColor = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
@Preview
private fun OnBoardingLoadingScreenPreview() {
    SkillBookTheme {
        OnBoardingLoadingScreen(
            uiState = OnBoardingUiState.Loading()
        )
    }
}

@Composable
@Preview
private fun OnBoardingPagerScreenPreview() {
    SkillBookTheme {
        OnBoardingPagerScreen(
            uiState = OnBoardingUiState.DataLoaded(pages = OnBoardingUiPages),
            onFinishOnBoarding = {}
        )
    }
}
