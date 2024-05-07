package com.jubayedalam.e_commerce_app.data.authentication.reset

data class ResetResponse (
    val status: String,
    val code: Int,
    val data: Map<String, Any>?, // Change to Map<String, Any>
    val message: String?,
    val errors: Map<String, List<String>>?
)
