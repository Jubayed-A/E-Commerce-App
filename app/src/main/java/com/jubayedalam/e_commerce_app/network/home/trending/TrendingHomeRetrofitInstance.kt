package com.jubayedalam.e_commerce_app.network.home.trending

import com.jubayedalam.e_commerce_app.utils.AppConstants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TrendingHomeRetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: TrendingHomeApiService by lazy {
        retrofit.create(TrendingHomeApiService::class.java)
    }
}