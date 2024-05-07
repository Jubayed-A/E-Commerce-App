package com.jubayedalam.e_commerce_app.network.authentication.newPassword

import com.jubayedalam.e_commerce_app.data.authentication.newPassword.NewPassword
import com.jubayedalam.e_commerce_app.data.authentication.newPassword.NewPasswordResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface NewPasswordApiService {
    @POST("api/v1/auth/forget/password/confirm/")
    suspend fun setNewPassword(@Body newPassword: NewPassword): Response<NewPasswordResponse>
}