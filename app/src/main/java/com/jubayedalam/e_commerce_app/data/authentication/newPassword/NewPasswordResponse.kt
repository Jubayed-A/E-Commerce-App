package com.jubayedalam.e_commerce_app.data.authentication.newPassword

data class NewPasswordResponse(
    val status: String,
    val code: Int,
    val data: Any?,
    val message: String?,
    val errors: Map<String, List<String>>?
)
