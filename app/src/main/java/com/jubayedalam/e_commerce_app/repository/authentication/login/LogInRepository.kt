package com.jubayedalam.e_commerce_app.repository.authentication.login

import com.jubayedalam.e_commerce_app.data.authentication.login.LogIn
import com.jubayedalam.e_commerce_app.data.authentication.login.LoginResponse
import com.jubayedalam.e_commerce_app.network.authentication.login.LogInApiService
import com.jubayedalam.e_commerce_app.utils.sharedPreferences.SharedPreferencesManager
import com.jubayedalam.e_commerce_app.utils.stateManagement.Response
import retrofit2.Call
import retrofit2.Callback

class LogInRepository(private val apiService: LogInApiService, private val sharedPreferencesManager: SharedPreferencesManager) {
    fun loginUser(loginRequest: LogIn, onResult: (Response<LoginResponse>) -> Unit) {
        apiService.loginUser(loginRequest).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(
                call: Call<LoginResponse>,
                response: retrofit2.Response<LoginResponse>
            ) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        /*sharedPreferencesManager.saveToken(body.data.token)
                        sharedPreferencesManager.saveUserId(body.data.id)*/
                        body.data?.let { sharedPreferencesManager.saveToken(it.token) }
                        body.data?.let { sharedPreferencesManager.saveUserId(it.id) }
                        onResult(Response.Success(body))
                    } else {
                        onResult(Response.Error(Exception("Empty response body").toString()))
                    }
                } else {
                    onResult(Response.Error(Exception("Failed to login").toString()))
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                onResult(Response.Error(Exception(t.message).toString()))
            }

        })
    }
}

