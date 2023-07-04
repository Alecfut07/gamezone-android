package com.alecfut07.gamezone.di

import com.alecfut07.gamezone.LoginRepository
import com.alecfut07.gamezone.LoginService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object LoginModule {

    @Provides
    fun getLoginRepository(loginService: LoginService): LoginRepository {
        return LoginRepository(loginService)
    }
}
