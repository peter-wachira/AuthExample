package com.droid.authexample.di

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.droid.authexample.data.UserPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object PreferencesModule {
    @Singleton
    @Provides
    fun provideUserPreferences(@ApplicationContext context:Context):UserPreferences =
        UserPreferences(context)
    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)
}