package com.example.myresume.dagger.module

import com.example.myresume.services.ResumeService
import com.example.myresume.util.ApiConstants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

/**
 * Network module
 */
@Module
class NetworkModule {

    @Provides
    @Singleton
    internal fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(JacksonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    internal fun provideResumeService(retrofit: Retrofit): ResumeService {
        return retrofit.create(ResumeService::class.java)
    }
}
