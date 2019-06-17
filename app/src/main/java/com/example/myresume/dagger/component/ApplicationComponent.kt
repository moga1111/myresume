package com.example.myresume.dagger.component


import com.example.myresume.dagger.module.ApplicationModule
import com.example.myresume.dagger.module.MainModule
import com.example.myresume.dagger.module.NetworkModule
import com.example.myresume.dagger.module.SharedPreferencesModule
import com.example.myresume.presenter.main.MainPresenter
import com.example.myresume.ui.main.MainActivity
import dagger.Component
import javax.inject.Singleton


/**
 * Dagger Application Component
 *
 * Modules:  ApplicationModule
 * NetworkModule
 * MainModule
 * SharedPreferencesModule
 */
@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class, MainModule::class, SharedPreferencesModule::class])
interface ApplicationComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
}
