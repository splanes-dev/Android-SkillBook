package com.splanes.apps.skillbook.ui.feature.onboarding.model

import androidx.annotation.StringRes
import com.splanes.apps.skillbook.ui.R

data class OnBoardingUiData(
    @StringRes val title: Int,
    @StringRes val description: Int
)

val OnBoardingUiPages = listOf(
    OnBoardingUiData(
        title = R.string.onboarding_welcome_page_title,
        description = R.string.onboarding_welcome_page_description
    ),
    OnBoardingUiData(
        title = R.string.onboarding_app_brief_page_title,
        description = R.string.onboarding_app_brief_page_description
    ),
    OnBoardingUiData(
        title = R.string.onboarding__page_title,
        description = R.string.onboarding__page_description
    )
)
