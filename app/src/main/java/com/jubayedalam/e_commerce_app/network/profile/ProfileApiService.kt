package com.jubayedalam.e_commerce_app.network.profile

import com.jubayedalam.e_commerce_app.data.profile.ProfileResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ProfileApiService {
    @GET("api/v1/auth/profile/")
    suspend fun getProfileUser(@Header("Authorization") token : String): Response<ProfileResponse>
}