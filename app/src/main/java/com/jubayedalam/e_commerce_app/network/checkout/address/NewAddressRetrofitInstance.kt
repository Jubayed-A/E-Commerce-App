package com.jubayedalam.e_commerce_app.network.checkout.address

import com.jubayedalam.e_commerce_app.utils.AppConstants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NewAddressRetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val newAddressApiService: NewAddressApiService by lazy {
        retrofit.create(NewAddressApiService::class.java)
    }
}