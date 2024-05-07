package com.jubayedalam.e_commerce_app.network.checkout.addressGet

import com.jubayedalam.e_commerce_app.data.checkout.addressGet.AddressResponse
import retrofit2.Response
import retrofit2.http.GET

interface AddressApiService {
    @GET("/api/v1/order/web/billing-info/?limit=3")
    suspend fun getAllAddresses(): Response<AddressResponse>
}
