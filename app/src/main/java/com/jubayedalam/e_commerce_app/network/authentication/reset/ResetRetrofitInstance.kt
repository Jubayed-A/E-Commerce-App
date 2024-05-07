package com.jubayedalam.e_commerce_app.network.authentication.reset

import com.jubayedalam.e_commerce_app.utils.AppConstants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ResetRetrofitInstance {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ResetApiService by lazy {
        retrofit.create(ResetApiService::class.java)
    }
}