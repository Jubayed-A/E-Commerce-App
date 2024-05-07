package com.jubayedalam.e_commerce_app.repository.checkout.addressGet

import com.jubayedalam.e_commerce_app.data.checkout.addressGet.Address
import com.jubayedalam.e_commerce_app.network.checkout.addressGet.AddressApiService
import com.jubayedalam.e_commerce_app.utils.stateManagement.Response

class AddressRepository(private val apiService: AddressApiService) {
    suspend fun getAllAddresses(): Response<List<Address>> {
        return try {
            val response = apiService.getAllAddresses()
            if (response.isSuccessful) {
                val addresses = response.body()?.data?.results
                if (addresses != null) {
                    Response.Success(addresses)
                } else {
                    Response.Error("No addresses found")
                }
            } else {
                Response.Error("Failed to fetch addresses")
            }
        } catch (e: Exception) {
            Response.Error("An error occurred: ${e.message}")
        }
    }
}
