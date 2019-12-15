package com.example.myresume.di.module

import android.app.Application
import com.example.myresume.eventbus.RxBus
import dagger.Module
import dagger.Provides

import javax.inject.Singleton

/**
 * Dagger Application module that provides the following dependencies:
 * - Application
 * - EventBus
 */
@Module
class ApplicationTestModule(private val application: Application) {

    @Provides
    @Singleton
    internal fun providesApplication(): Application {
        return application
    }

    @Provides
    @Singleton
    internal fun provideRxBus(): RxBus {
        return RxBus()
    }
}
