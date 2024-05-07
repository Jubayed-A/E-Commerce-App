package com.jubayedalam.e_commerce_app.viewModel.authentication.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jubayedalam.e_commerce_app.repository.authentication.login.LogInRepository
import com.jubayedalam.e_commerce_app.utils.sharedPreferences.SharedPreferencesManager

class LoginViewModelFactory(
    private val logInRepository: LogInRepository,
    private val sharedPreferencesManager: SharedPreferencesManager
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(logInRepository, sharedPreferencesManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
