package com.splanes.apps.skillbook.data.infrastructure.di

import com.splanes.apps.skillbook.data.feature.onboarding.datasource.OnBoardingDataSource
import com.splanes.apps.skillbook.data.feature.onboarding.datasource.impl.OnBoardingDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindOnBoardingDataSource(impl: OnBoardingDataSourceImpl): OnBoardingDataSource
}
