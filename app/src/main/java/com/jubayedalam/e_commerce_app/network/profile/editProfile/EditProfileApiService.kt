package com.jubayedalam.e_commerce_app.network.profile.editProfile

import com.jubayedalam.e_commerce_app.data.profile.editProfile.EditProfileResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface EditProfileApiService {
    /*@Multipart
    @FormUrlEncoded
    @POST("api/v1/auth/profile/")
    suspend fun updatedUserInfo(@Part editProfileUser: EditProfileUser, @Header("Authorization") token : String) : Response<EditProfileResponse>*/
/*
    @Multipart
    @FormUrlEncoded
    @POST("api/v1/auth/profile/")
    suspend fun updatedUserInfo(@Part editProfileUser: MultipartBody.Part, @Header("Authorization") token : String) : Response<EditProfileResponse>
*/

    @Multipart
    @POST("api/v1/auth/profile/")
    suspend fun updateUserInfo(
        @Part("id") id: RequestBody,
        @Part("first_name") firstName: RequestBody,
        @Part("last_name") lastName: RequestBody,
        @Part("email") email: RequestBody,
        @Part("mobile") mobile: RequestBody?,
        @Part("billing_info") billingInfo: RequestBody?,
//        @Part("billing_info_details") billingInfoDetails: RequestBody?,
        @Part("hide_section") hideSection: RequestBody?,
        @Part image: MultipartBody.Part?,
        @Header("Authorization") token: String
    ): Response<EditProfileResponse>


}