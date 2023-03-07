package com.splanes.apps.skillbook.data.infrastructure.di

import com.splanes.apps.skillbook.data.feature.profile.datasource.impl.ProfileCache
import com.splanes.apps.skillbook.data.feature.studies.datasource.impl.StudiesCache
import com.splanes.apps.skillbook.data.feature.workhistory.datasource.impl.WorkHistoryCache
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Provides
    fun providesProfileCache(): ProfileCache = ProfileCache

    @Provides
    fun providesStudiesCache(): StudiesCache = StudiesCache

    @Provides
    fun providesWorkHistoryCache(): WorkHistoryCache = WorkHistoryCache
}
