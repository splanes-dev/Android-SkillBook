package com.splanes.apps.skillbook.data.infrastructure.di

import com.splanes.apps.skillbook.data.feature.onboarding.repository.OnBoardingRepositoryImpl
import com.splanes.apps.skillbook.domain.feature.onboarding.repository.OnBoardingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindOnBoardingRepository(impl: OnBoardingRepositoryImpl): OnBoardingRepository
}
