package com.jubayedalam.e_commerce_app.network.checkout.placeOrder

import com.jubayedalam.e_commerce_app.utils.AppConstants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CheckoutRetrofitInstance {

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val checkoutApiService: CheckoutApiService by lazy {
        retrofit.create(CheckoutApiService::class.java)
    }

}