package com.jubayedalam.e_commerce_app.network.profile

import com.jubayedalam.e_commerce_app.utils.AppConstants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ProfileRetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ProfileApiService by lazy {
        retrofit.create(ProfileApiService::class.java)
    }
}