package com.alecfut07.gamezone

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
): ViewModel() {
    fun login(email: String, password: String) {
        loginRepository.login(email, password) { accessToken ->
            if (accessToken != null) println("This is the access token $accessToken")
            else println("empty token")
        }
    }
}