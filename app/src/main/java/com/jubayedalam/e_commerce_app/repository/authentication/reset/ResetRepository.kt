package com.jubayedalam.e_commerce_app.repository.authentication.reset

import com.jubayedalam.e_commerce_app.data.authentication.reset.ResetPassword
import com.jubayedalam.e_commerce_app.data.authentication.reset.ResetResponse
import com.jubayedalam.e_commerce_app.network.authentication.reset.ResetApiService
import com.jubayedalam.e_commerce_app.utils.stateManagement.Response

class ResetRepository(private val apiService: ResetApiService) {

    suspend fun sendUserEmail(userRequest: ResetPassword): Response<ResetResponse> {
        return try {
            val response = apiService.sendUserEmail(userRequest)
            if (response.isSuccessful) {
                Response.Success(response.body()!!)
            } else {
                Response.Error(response.errorBody().toString())
            }
        } catch (e: Exception) {
            Response.Error(e.message ?: "Unknown error")
        }
    }
}
