package com.alecfut07.gamezone

import android.widget.Button
import android.widget.EditText
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.robolectric.annotation.Config

@HiltAndroidTest
@Config(application = HiltTestApplication::class)
@RunWith(AndroidJUnit4::class)
class LoginFragmentTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

//    @get:Rule
//    var rule: TestRule = InstantTaskExecutorRule()

    @BindValue
    val loginViewModel = mock<LoginViewModel>()

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun testEventFragment() {
        launchFragmentInHiltContainer<LoginFragment> {
            val button = this.view?.findViewById<Button>(R.id.login_button)
            assertThat(button?.text.toString()).isEqualTo("Login")
        }
    }

    @Test
    fun `clicking on the login button calls login method on view model`() {
        launchFragmentInHiltContainer<LoginFragment> {
            val emailEditText = this.view?.findViewById<EditText>(R.id.email_edit_text)
            val email = "email@gmail.com"
            emailEditText?.setText(email)
            val passwordEditText = this.view?.findViewById<EditText>(R.id.password_edit_text)
            val password = "123456"
            passwordEditText?.setText(password)
            val button = this.view?.findViewById<Button>(R.id.login_button)
            button?.performClick()

            verify(loginViewModel).login(email, password)
        }
    }
}
