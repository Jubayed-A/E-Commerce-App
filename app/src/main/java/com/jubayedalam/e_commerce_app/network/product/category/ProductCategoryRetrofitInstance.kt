package com.jubayedalam.e_commerce_app.network.product.category

import com.jubayedalam.e_commerce_app.utils.AppConstants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ProductCategoryRetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ProductCategoryApiService by lazy {
        retrofit.create(ProductCategoryApiService::class.java)
    }
}