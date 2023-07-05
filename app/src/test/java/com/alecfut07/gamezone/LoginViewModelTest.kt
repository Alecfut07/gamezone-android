package com.alecfut07.gamezone

import android.content.Context
import android.content.SharedPreferences
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.doNothing
import org.mockito.kotlin.eq
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class LoginViewModelTest {
    @Test
    fun `calls loging`() {
        val email = "test@gmail.com"
        val password = "123456"
        val accessToken = "token"

        val loginRepository = mock<LoginRepository>()
        val captor = argumentCaptor<(String?) -> Unit>()
        doNothing().whenever(loginRepository).login(eq(email), eq(password), captor.capture())
        val applicationContext = mock<Context>()
        val sharedPref = mock<SharedPreferences>()
        whenever(applicationContext.getSharedPreferences("shared_pref", Context.MODE_PRIVATE)).thenReturn(
            sharedPref
        )
        val sharedPrefEditor = mock<SharedPreferences.Editor>()
        whenever(sharedPref.edit()).thenReturn(sharedPrefEditor)
        whenever(sharedPrefEditor.putString("access_token", accessToken)).thenReturn(sharedPrefEditor)

        val loginViewModel = LoginViewModel(applicationContext, loginRepository)

        loginViewModel.login(email, password)

        val arg = captor.firstValue
        arg(accessToken)
        verify(sharedPrefEditor).apply()
    }
}
