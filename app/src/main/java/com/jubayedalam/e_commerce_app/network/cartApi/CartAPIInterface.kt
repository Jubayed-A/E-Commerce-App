package com.jubayedalam.e_commerce_app.network.cartApi

import com.jubayedalam.e_commerce_app.data.cart.CartTestData
import retrofit2.Response
import retrofit2.http.GET

interface CartAPIInterface {

    @GET("/posts?_limit=10")
    suspend fun getAllUsers():Response<CartTestData>

}