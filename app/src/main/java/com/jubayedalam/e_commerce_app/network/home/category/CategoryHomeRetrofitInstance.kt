package com.jubayedalam.e_commerce_app.network.home.category

import com.jubayedalam.e_commerce_app.utils.AppConstants
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CategoryHomeRetrofitInstance {

    private val retrofit = Retrofit.Builder()
        .baseUrl(AppConstants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .client(OkHttpClient.Builder().build())
        .build()

    val apiService: CategoryHomeApiService = retrofit.create(CategoryHomeApiService::class.java)
}