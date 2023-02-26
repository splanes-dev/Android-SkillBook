package com.splanes.apps.skillbook.data.feature.onboarding.datasource.impl

import android.content.SharedPreferences
import androidx.core.content.edit
import com.splanes.apps.skillbook.data.feature.onboarding.datasource.OnBoardingDataSource
import javax.inject.Inject

class OnBoardingDataSourceImpl @Inject constructor(
    private val preferences: SharedPreferences
) : OnBoardingDataSource {

    override suspend fun getVisibility(): Boolean =
        preferences.getBoolean(OnBoardingPreferences, true)

    override suspend fun updateOrInsertVisibility(isVisible: Boolean) =
        preferences.edit {
            putBoolean(OnBoardingPreferences, isVisible)
        }
}

private const val OnBoardingPreferences = "pref.onboarding"
