package com.alecfut07.gamezone

import android.content.Context
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    @ApplicationContext private val applicationContext: Context,
    private val loginRepository: LoginRepository
): ViewModel() {
    companion object {
        const val SHARED_PREF = "shared_pref"
        const val ACCESS_TOKEN_KEY = "access_token"
    }
    fun login(email: String, password: String) {
        loginRepository.login(email, password) { accessToken ->
            if (accessToken != null) {
                val sharedPref = applicationContext.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
                sharedPref.edit().putString(ACCESS_TOKEN_KEY, accessToken).apply()
            } else {
                println("empty token")
            }
        }
    }
}