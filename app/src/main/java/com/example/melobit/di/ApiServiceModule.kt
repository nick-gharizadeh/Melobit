package com.example.melobit.di

import com.example.melobit.network.MelobitApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object ApiServiceModule {
    @Singleton
    @Provides
    fun getApiService(retrofit: Retrofit): MelobitApiService {
        return retrofit.create(MelobitApiService::class.java)
    }
}