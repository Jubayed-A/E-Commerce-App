package com.jubayedalam.e_commerce_app.repository.authentication.register

import com.jubayedalam.e_commerce_app.data.authentication.register.Register
import com.jubayedalam.e_commerce_app.data.authentication.register.RegisterResponse
import com.jubayedalam.e_commerce_app.network.authentication.register.RegisterApiService
import com.jubayedalam.e_commerce_app.utils.stateManagement.Response
import retrofit2.Call
import retrofit2.Callback

class RegisterRepository (private val apiService: RegisterApiService) {

    fun registerUser(user: Register, onResult: (Response<RegisterResponse>) -> Unit) {
        apiService.registerUser(user).enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: retrofit2.Response<RegisterResponse>
            ) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        onResult(Response.Success(body))
                    } else {
                        onResult(Response.Error(Exception("Empty response body").toString()))
                    }
                } else {
                    onResult(Response.Error(Exception("Failed to register $").toString()))
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                onResult(Response.Error(Exception(t.message).toString()))
            }


        })
    }

}