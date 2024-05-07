package com.jubayedalam.e_commerce_app.network.home.imageSlider

import com.jubayedalam.e_commerce_app.utils.AppConstants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ImageSliderRetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ImageSliderApiService by lazy {
        retrofit.create(ImageSliderApiService::class.java)
    }
}