package com.splanes.apps.skillbook.ui.feature.onboarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
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
import com.splanes.apps.skillbook.ui.theme.SkillBookTheme
import com.splanes.apps.skillbook.ui.theme.typographies.TypographyScheme

@Composable
fun OnBoardingLoadingScreen(
    uiState: OnBoardingUiState.Loading
) {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator()
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

    Box(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            state = pageState,
            count = pages.count()
        ) { index ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = pages[index].title),
                    style = TypographyScheme.headlineMedium
                )

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = stringResource(id = pages[index].description),
                    style = TypographyScheme.titleSmall
                )

                Spacer(modifier = Modifier.weight(1f))

                HorizontalPagerIndicator(pagerState = pageState)

                AnimatedVisibility(
                    visible = index == pages.lastIndex,
                    enter = expandVertically(animationSpec = tween(durationMillis = 500)),
                    exit = shrinkVertically(animationSpec = tween(durationMillis = 500))
                ) {
                    Button(onClick = onFinishOnBoarding) {
                        Text(
                            text = stringResource(id = R.string.start),
                            style = TypographyScheme.titleSmall
                        )
                    }
                }
            }
        }
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
            uiState = OnBoardingUiState.DataLoaded(),
            onFinishOnBoarding = {}
        )
    }
}
