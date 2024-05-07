package com.jubayedalam.e_commerce_app.network.checkout.addressGet

import com.jubayedalam.e_commerce_app.utils.AppConstants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AddressRetrofitInstance {

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val addressApiService: AddressApiService by lazy {
        retrofit.create(AddressApiService::class.java)
    }
}
