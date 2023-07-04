package com.alecfut07.gamezone

import okhttp3.Headers
import okhttp3.ResponseBody
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.stubbing.Answer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRepositoryTest {
    @Test
    fun `login return access token when response is successful`() {
        val email = "test@gmail.com"
        val password = "123456"
        val accessToken = "token"
        val signInRequest = SignInRequest(email, password)
        val map = mapOf(
            "Authorization" to "Bearer $accessToken"
        )
        val headers = Headers.of(map)
        val response: Response<Unit> = Response.success(null, headers)
        val call = mock<Call<Unit>>()
        val answer = Answer {
            val arg = it.getArgument<Callback<Unit>>(0)
            arg.onResponse(call, response)
        }
        `when`(call.enqueue(any())).thenAnswer(answer)
        val loginService = mock<LoginService>()
        `when`(loginService.login(signInRequest)).thenReturn(call)

        val loginRepository = LoginRepository(loginService)
        val callback = mock<(String?) -> Unit>()

        loginRepository.login(email, password, callback)

        verify(callback).invoke(accessToken)
    }

    @Test
    fun `login return access token when response is not successful`() {
        val email = "test@gmail.com"
        val password = "123456"
        val signInRequest = SignInRequest(email, password)
        val mock = mock<ResponseBody>()
        val response: Response<Unit> = Response.error(500, mock)
        val call = mock<Call<Unit>>()
        val answer = Answer {
            val arg = it.getArgument<Callback<Unit>>(0)
            arg.onResponse(call, response)
        }
        `when`(call.enqueue(any())).thenAnswer(answer)
        val loginService = mock<LoginService>()
        `when`(loginService.login(signInRequest)).thenReturn(call)

        val loginRepository = LoginRepository(loginService)
        val callback = mock<(String?) -> Unit>()

        loginRepository.login(email, password, callback)

        verify(callback).invoke(null)
    }

    @Test
    fun `login return access token when call fails`() {
        val email = "test@gmail.com"
        val password = "123456"
        val signInRequest = SignInRequest(email, password)
        val call = mock<Call<Unit>>()
        val answer = Answer {
            val arg = it.getArgument<Callback<Unit>>(0)
            arg.onFailure(call, Throwable())
        }
        `when`(call.enqueue(any())).thenAnswer(answer)
        val loginService = mock<LoginService>()
        `when`(loginService.login(signInRequest)).thenReturn(call)

        val loginRepository = LoginRepository(loginService)
        val callback = mock<(String?) -> Unit>()

        loginRepository.login(email, password, callback)

        verify(callback).invoke(null)
    }
}