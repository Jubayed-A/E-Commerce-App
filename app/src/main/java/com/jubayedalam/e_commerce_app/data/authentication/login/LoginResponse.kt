package com.jubayedalam.e_commerce_app.data.authentication.login

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("status") val status: String,
    @SerializedName("code") val code: Int,
    @SerializedName("data") val data: UserData?,
    @SerializedName("message") val message: String?
)

data class UserData(
    @SerializedName("id") val id: Int,
    @SerializedName("email") val email: String,
    @SerializedName("mobile") val mobile: String?,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("image") val image: String,
    @SerializedName("gender") val gender: String?,
    @SerializedName("role") val role: String,
    @SerializedName("permissions") val permissions: List<String>,
    @SerializedName("is_active") val isActive: Boolean,
    @SerializedName("is_superuser") val isSuperuser: Boolean,
    @SerializedName("is_customer") val isCustomer: Boolean,
    @SerializedName("billing_info") val billingInfo: Any?, // Change the type if necessary
    @SerializedName("hide_section") val hideSection: Any?, // Change the type if necessary
    @SerializedName("token") val token: String
)
