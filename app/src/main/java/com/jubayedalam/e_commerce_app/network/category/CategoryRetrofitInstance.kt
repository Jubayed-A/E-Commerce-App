package com.jubayedalam.e_commerce_app.network.category

import com.jubayedalam.e_commerce_app.utils.AppConstants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CategoryRetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: CategoryApiService by lazy {
        retrofit.create(CategoryApiService::class.java)
    }
}