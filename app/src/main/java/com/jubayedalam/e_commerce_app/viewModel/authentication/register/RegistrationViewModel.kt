package com.jubayedalam.e_commerce_app.viewModel.authentication.register

import androidx.lifecycle.ViewModel
import com.jubayedalam.e_commerce_app.data.authentication.register.Register
import com.jubayedalam.e_commerce_app.data.authentication.register.RegisterResponse
import com.jubayedalam.e_commerce_app.network.authentication.register.RegisterApiService
import com.jubayedalam.e_commerce_app.repository.authentication.register.RegisterRepository
import com.jubayedalam.e_commerce_app.utils.stateManagement.Response
import com.jubayedalam.e_commerce_app.utils.AppConstants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegistrationViewModel : ViewModel() {
    private val apiService: RegisterApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RegisterApiService::class.java)

    private val userRepository = RegisterRepository(apiService)

    fun registerUser(user: Register, onResult: (Response<RegisterResponse>) -> Unit) {
        userRepository.registerUser(user, onResult)
    }
}