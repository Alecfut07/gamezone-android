package com.alecfut07.gamezone

import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    private val loginRepository = LoginRepository()


    fun login(email: String, password: String) {
        loginRepository.login(email, password) { accessToken ->
            if (accessToken != null) println("This is the access token $accessToken")
            else println("empty token")
        }
    }
}