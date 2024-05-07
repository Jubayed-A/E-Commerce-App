package com.jubayedalam.e_commerce_app.network.product.item

import com.jubayedalam.e_commerce_app.utils.AppConstants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ProductItemRetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ProductItemApiServices by lazy {
        retrofit.create(ProductItemApiServices::class.java)
    }

}