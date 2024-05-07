package com.jubayedalam.e_commerce_app.network.authentication.login

import com.jubayedalam.e_commerce_app.data.authentication.login.LogIn
import com.jubayedalam.e_commerce_app.data.authentication.login.LoginResponse
import com.jubayedalam.e_commerce_app.utils.AppConstants.Companion.BASE_URL
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface LogInApiService {
    @POST("api/v1/auth/login/")
    fun loginUser(@Body loginRequest: LogIn): Call<LoginResponse>

    companion object{
        fun create(): LogInApiService{
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(LogInApiService::class.java)
        }
    }

}
