package com.ihsanarslan.onboardingscreen.di

import android.content.Context
import com.ihsanarslan.onboardingscreen.data.local.preferences.AppDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDatabaseModule {

    @Provides
    @Singleton
    fun provideAppDataStore(@ApplicationContext appContext: Context): AppDataStore {
        return AppDataStore(appContext)
    }

}