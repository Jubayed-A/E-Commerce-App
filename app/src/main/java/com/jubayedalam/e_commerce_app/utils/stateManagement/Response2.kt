package com.jubayedalam.e_commerce_app.utils.stateManagement

open class Response2 <T> internal constructor(val data: T? = null, val errorMessage: String? = null) {
    class Loading<T> : Response2<T>()
    class Success<T>(data: T? = null) : Response2<T>(data = data)
    class Error<T>(errorMessage: String) : Response2<T>(errorMessage = errorMessage)
}