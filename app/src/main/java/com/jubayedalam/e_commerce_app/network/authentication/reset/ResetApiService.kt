package com.jubayedalam.e_commerce_app.network.authentication.reset

import com.jubayedalam.e_commerce_app.data.authentication.reset.ResetPassword
import com.jubayedalam.e_commerce_app.data.authentication.reset.ResetResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ResetApiService {
    @POST("api/v1/auth/forget/password/")
    suspend fun sendUserEmail(@Body userRequest: ResetPassword): Response<ResetResponse>
}