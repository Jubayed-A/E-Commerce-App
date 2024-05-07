package com.jubayedalam.e_commerce_app.network.authentication.register

import com.jubayedalam.e_commerce_app.data.authentication.register.Register
import com.jubayedalam.e_commerce_app.data.authentication.register.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterApiService{
    @POST("api/v1/auth/signup/")
    fun registerUser(@Body user: Register): Call<RegisterResponse>
}

