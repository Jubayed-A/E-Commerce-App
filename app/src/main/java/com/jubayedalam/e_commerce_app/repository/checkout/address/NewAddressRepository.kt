package com.jubayedalam.e_commerce_app.repository.checkout.address

import com.jubayedalam.e_commerce_app.data.checkout.address.NewAddress
import com.jubayedalam.e_commerce_app.data.checkout.address.NewAddressResponseData
import com.jubayedalam.e_commerce_app.network.checkout.address.NewAddressApiService
import com.jubayedalam.e_commerce_app.utils.stateManagement.Response

class NewAddressRepository(private val apiService: NewAddressApiService) {
    suspend fun setNewAddress(newAddress: NewAddress): Response<NewAddressResponseData> {
        return try {
            val response = apiService.setNewAddress(newAddress)
            Response.Success(response.body()!!)
        } catch (e: Exception) {
            Response.Error(e.message ?: "Unknown error")
        }
    }
}