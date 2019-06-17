package com.example.myresume.dagger.module


import com.example.myresume.eventbus.RxBus
import com.example.myresume.presenter.main.MainPresenter
import com.example.myresume.presenter.main.MainViewPresenter
import dagger.Module
import dagger.Provides

import javax.inject.Singleton

/**
 * Main module that provides the following dependencies:
 * - MainViewPresenter (MainPresenter)
 */
@Module
class MainModule {
    @Provides
    @Singleton
    internal fun provideMainPresenter(rxBus: RxBus): MainViewPresenter {
        return MainPresenter(rxBus)
    }
}
