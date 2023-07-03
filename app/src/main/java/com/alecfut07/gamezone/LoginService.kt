package com.alecfut07.gamezone

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("api/users/sign_in")
    fun login(@Body signInRequest: SignInRequest?): Call<Unit>
}