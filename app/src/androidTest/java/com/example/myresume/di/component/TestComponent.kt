package com.example.myresume.di.component

import com.example.myresume.dagger.component.ApplicationComponent
import com.example.myresume.dagger.module.MainModule
import com.example.myresume.dagger.module.SharedPreferencesModule
import com.example.myresume.di.module.ApplicationTestModule
import com.example.myresume.di.module.NetworkTestModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [ApplicationTestModule::class,  NetworkTestModule::class, MainModule::class, SharedPreferencesModule::class])
interface TestComponent : ApplicationComponent