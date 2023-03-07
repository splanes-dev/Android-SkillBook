package com.splanes.apps.skillbook.data.infrastructure.di

import com.splanes.apps.skillbook.data.feature.onboarding.datasource.OnBoardingDataSource
import com.splanes.apps.skillbook.data.feature.onboarding.datasource.impl.OnBoardingDataSourceImpl
import com.splanes.apps.skillbook.data.feature.profile.datasource.ProfileCacheDataSource
import com.splanes.apps.skillbook.data.feature.profile.datasource.ProfileRemoteDataSource
import com.splanes.apps.skillbook.data.feature.profile.datasource.impl.ProfileCache
import com.splanes.apps.skillbook.data.feature.profile.datasource.impl.ProfileRemoteDataSourceImpl
import com.splanes.apps.skillbook.data.feature.studies.datasource.StudiesCacheDataSource
import com.splanes.apps.skillbook.data.feature.studies.datasource.StudiesRemoteDataSource
import com.splanes.apps.skillbook.data.feature.studies.datasource.impl.StudiesCache
import com.splanes.apps.skillbook.data.feature.studies.datasource.impl.StudiesRemoteDataSourceImpl
import com.splanes.apps.skillbook.data.feature.workhistory.datasource.WorkHistoryCacheDataSource
import com.splanes.apps.skillbook.data.feature.workhistory.datasource.WorkHistoryRemoteDataSource
import com.splanes.apps.skillbook.data.feature.workhistory.datasource.impl.WorkHistoryCache
import com.splanes.apps.skillbook.data.feature.workhistory.datasource.impl.WorkHistoryRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindOnBoardingDataSource(impl: OnBoardingDataSourceImpl): OnBoardingDataSource

    @Binds
    abstract fun bindProfileCacheDataSource(cache: ProfileCache): ProfileCacheDataSource

    @Binds
    abstract fun bindProfileRemoteDataSource(
        impl: ProfileRemoteDataSourceImpl
    ): ProfileRemoteDataSource

    @Binds
    abstract fun bindStudiesCacheDataSource(cache: StudiesCache): StudiesCacheDataSource

    @Binds
    abstract fun bindStudiesRemoteDataSource(
        impl: StudiesRemoteDataSourceImpl
    ): StudiesRemoteDataSource

    @Binds
    abstract fun bindWorkHistoryCacheDataSource(cache: WorkHistoryCache): WorkHistoryCacheDataSource

    @Binds
    abstract fun bindWorkHistoryRemoteDataSource(
        impl: WorkHistoryRemoteDataSourceImpl
    ): WorkHistoryRemoteDataSource
}
