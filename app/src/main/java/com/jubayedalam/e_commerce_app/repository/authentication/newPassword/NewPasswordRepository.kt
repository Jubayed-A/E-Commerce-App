package com.jubayedalam.e_commerce_app.repository.authentication.newPassword

import com.jubayedalam.e_commerce_app.data.authentication.newPassword.NewPassword
import com.jubayedalam.e_commerce_app.data.authentication.newPassword.NewPasswordResponse
import com.jubayedalam.e_commerce_app.network.authentication.newPassword.NewPasswordApiService
import com.jubayedalam.e_commerce_app.utils.stateManagement.Response

class NewPasswordRepository(private val apiService: NewPasswordApiService) {

    suspend fun setNewPassword(newPassword: NewPassword): Response<NewPasswordResponse> {
        return try {
            val response = apiService.setNewPassword(newPassword)
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
