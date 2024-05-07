package com.jubayedalam.e_commerce_app.repository.profile

import com.jubayedalam.e_commerce_app.data.profile.ProfileResponse
import com.jubayedalam.e_commerce_app.network.profile.ProfileApiService
import com.jubayedalam.e_commerce_app.utils.stateManagement.Response
import java.io.IOException

class ProfileRepository(private val apiService: ProfileApiService) {
    suspend fun getProfileUser(token : String): Response<ProfileResponse> {
        return try {
            val response = apiService.getProfileUser(token)
            if (response.isSuccessful) {
                response.body()?.let {
                    Response.Success(it)
                } ?: Response.Error("Response body is null")
            } else {
                Response.Error("Error: ${response.code()} ${response.message()}")
            }
        } catch (e: IOException) {
            Response.Error("Network error: ${e.message}")
        } catch (e: Exception) {
            Response.Error("Unexpected error: ${e.message}")
        }
    }
}
