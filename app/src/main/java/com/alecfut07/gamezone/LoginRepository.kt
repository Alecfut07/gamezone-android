package com.alecfut07.gamezone

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRepository(
    private val loginService: LoginService
) {
    fun login(email: String, password: String, callback: (String?) -> Unit) {
        val signInRequest = SignInRequest(email, password)
        loginService.login(signInRequest).enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                val accessToken = response.headers().get("Authorization")
                    ?.split(" ")
                    ?.lastOrNull()

                callback(accessToken)
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}