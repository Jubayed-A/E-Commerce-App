package com.jubayedalam.e_commerce_app.utils

import com.jubayedalam.e_commerce_app.network.cartApi.CartAPIInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {

    val api: CartAPIInterface by lazy {
        Retrofit.Builder()
            .baseUrl(Utils.BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CartAPIInterface::class.java)
    }

}