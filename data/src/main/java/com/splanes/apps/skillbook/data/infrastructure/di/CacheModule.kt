package com.splanes.apps.skillbook.data.infrastructure.di

import com.splanes.apps.skillbook.data.feature.profile.datasource.impl.ProfileCache
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Provides
    fun providesProfileCache(): ProfileCache = ProfileCache
}
