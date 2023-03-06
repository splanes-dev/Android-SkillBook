package com.splanes.apps.skillbook.data.infrastructure.di

import com.splanes.apps.skillbook.data.feature.onboarding.repository.OnBoardingRepositoryImpl
import com.splanes.apps.skillbook.data.feature.profile.repository.ProfileRepositoryImpl
import com.splanes.apps.skillbook.data.feature.studies.repository.StudiesRepositoryImpl
import com.splanes.apps.skillbook.domain.feature.onboarding.repository.OnBoardingRepository
import com.splanes.apps.skillbook.domain.feature.profile.repository.ProfileRepository
import com.splanes.apps.skillbook.domain.feature.studies.repository.StudiesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindOnBoardingRepository(impl: OnBoardingRepositoryImpl): OnBoardingRepository

    @Binds
    abstract fun bindProfileRepository(impl: ProfileRepositoryImpl): ProfileRepository

    @Binds
    abstract fun bindStudiesRepository(impl: StudiesRepositoryImpl): StudiesRepository
}
