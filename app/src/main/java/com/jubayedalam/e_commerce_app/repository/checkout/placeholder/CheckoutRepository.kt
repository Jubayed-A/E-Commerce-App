package com.jubayedalam.e_commerce_app.repository.checkout.placeholder

import com.jubayedalam.e_commerce_app.data.checkout.placeOrder.CheckoutRequest
import com.jubayedalam.e_commerce_app.data.checkout.placeOrder.CheckoutResponse
import com.jubayedalam.e_commerce_app.network.checkout.placeOrder.CheckoutApiService
import com.jubayedalam.e_commerce_app.utils.stateManagement.Response

class CheckoutRepository(private val apiService: CheckoutApiService) {
    suspend fun setCheckout(checkout: CheckoutRequest): Response<CheckoutResponse> {
        return try {
            val response = apiService.checkout(checkout)
            Response.Success(response.body()!!)
        } catch (e: Exception) {
            Response.Error(e.message ?: "Unknown error")
        }
    }
}