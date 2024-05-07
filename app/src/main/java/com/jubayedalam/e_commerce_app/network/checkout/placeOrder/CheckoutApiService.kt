package com.jubayedalam.e_commerce_app.network.checkout.placeOrder


import com.jubayedalam.e_commerce_app.data.checkout.placeOrder.CheckoutRequest
import com.jubayedalam.e_commerce_app.data.checkout.placeOrder.CheckoutResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CheckoutApiService {
    @POST("api/v1/order/web/checkout/")
    suspend fun checkout(@Body checkoutRequest: CheckoutRequest) : Response<CheckoutResponse>
}