package com.alecfut07.gamezone.di

import com.alecfut07.gamezone.LoginService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    fun providesLoginService(retrofit: Retrofit): LoginService = retrofit.create(LoginService::class.java)
}
