package com.alecfut07.gamezone

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class LoginRepository {
    private val retrofit: Retrofit

    init {

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
    }

    fun login(email: String, password: String, callback: (String?) -> Unit)  {
        val loginService = retrofit.create(LoginService::class.java)
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