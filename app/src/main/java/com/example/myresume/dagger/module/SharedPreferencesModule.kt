package com.example.myresume.dagger.module

import android.content.Context
import com.example.myresume.model.SharedPreferencesRepositoryImplementation
import com.example.myresume.model.interfaces.SharedPreferencesRepository
import dagger.Module
import dagger.Provides

import javax.inject.Singleton

/**
 * Dagger Application module that provides the following dependencies:
 * - SharedPreferences
 */
@Module
class SharedPreferencesModule(private val context: Context) {

    @Provides
    @Singleton
    internal fun provideSharedPreferences(): SharedPreferencesRepository {
        return SharedPreferencesRepositoryImplementation(context)
    }
}
