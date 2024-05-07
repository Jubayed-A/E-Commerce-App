package com.jubayedalam.e_commerce_app.network.checkout.address

import com.jubayedalam.e_commerce_app.data.checkout.address.NewAddress
import com.jubayedalam.e_commerce_app.data.checkout.address.NewAddressResponseData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface NewAddressApiService {
    @POST("api/v1/order/web/billing-info/")
    suspend fun setNewAddress(@Body newAddress: NewAddress): Response<NewAddressResponseData>
}