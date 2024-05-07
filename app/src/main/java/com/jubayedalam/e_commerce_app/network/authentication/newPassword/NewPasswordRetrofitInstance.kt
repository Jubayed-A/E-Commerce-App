package com.jubayedalam.e_commerce_app.network.authentication.newPassword

import com.jubayedalam.e_commerce_app.utils.AppConstants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NewPasswordRetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val newPasswordApiService: NewPasswordApiService by lazy {
        retrofit.create(NewPasswordApiService::class.java)
    }
}