package com.example.myresume

import android.app.Application
import com.example.myresume.dagger.component.ApplicationComponent
import com.example.myresume.dagger.component.DaggerApplicationComponent
import com.example.myresume.dagger.module.ApplicationModule
import com.example.myresume.dagger.module.NetworkModule
import com.example.myresume.dagger.module.SharedPreferencesModule

/**
 * Main Application
 */
class MainApplication : Application() {

    //region Fields & Variables
    /**
     * Application Component
     */
    //endregion

    //region Public Methods
    /**
     * Application Component Getter
     * @return the application component
     */
    var component: ApplicationComponent? = null
        private set
    //endregion

    //region Life Cycle Methods
    override fun onCreate() {
        super.onCreate()
        initInjector()
    }
    //endregion

    //region Private Methods
    /**
     * Initialize the injector
     */
    private fun initInjector() {
        component = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .networkModule(NetworkModule())
            .sharedPreferencesModule(SharedPreferencesModule(this))
            .build()
    }
    //endregion

}
