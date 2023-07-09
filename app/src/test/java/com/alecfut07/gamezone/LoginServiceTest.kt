package com.alecfut07.gamezone

import com.google.common.truth.Truth.assertThat
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

class LoginServiceTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var retrofit: Retrofit

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `sign in returns access token when response is successful`() {
        val response = MockResponse()
            .addHeader("Content-Type", "application/json; charset=utf-8")
            .setHeader("Authorization", "Bearer token")
            .setResponseCode(HttpURLConnection.HTTP_NO_CONTENT)
        mockWebServer.enqueue(response)

        val loginService = retrofit.create(LoginService::class.java)

        val signInRequest = SignInRequest(
            email = "email@gmail.com",
            password = "123456"
        )
        val actualResponse = loginService.login(signInRequest).execute()
        val header = actualResponse.headers().get("Authorization")

        assertThat(header).isEqualTo("Bearer token")
    }
}
