package com.jubayedalam.e_commerce_app.viewModel.authentication.login

import androidx.lifecycle.ViewModel
import com.jubayedalam.e_commerce_app.data.authentication.login.LogIn
import com.jubayedalam.e_commerce_app.data.authentication.login.LoginResponse
import com.jubayedalam.e_commerce_app.repository.authentication.login.LogInRepository
import com.jubayedalam.e_commerce_app.utils.sharedPreferences.SharedPreferencesManager
import com.jubayedalam.e_commerce_app.utils.stateManagement.Response

class LoginViewModel(
    private val logInRepository: LogInRepository,
    private val sharedPreferencesManager: SharedPreferencesManager
) : ViewModel() {
    fun loginUser(email: String, password: String, onResult: (Response<LoginResponse>) -> Unit) {
        val loginRequest = LogIn(email, password)
        logInRepository.loginUser(loginRequest, onResult)
    }

    fun isLoggedIn(): Boolean {
        return sharedPreferencesManager.getToken() != null
    }
}


/*package com.devsstore.devsstore.viewModel.authentication.login

import androidx.lifecycle.ViewModel
import com.devsstore.devsstore.data.authentication.login.LogIn
import com.devsstore.devsstore.repository.authentication.login.LogInRepository
import com.devsstore.devsstore.utils.stateManagement.Response
import com.devsstore.devsstore.utils.sharedPreferences.SharedPreferencesManager

class LoginViewModel(
    private val logInRepository: LogInRepository,
    private val sharedPreferencesManager: SharedPreferencesManager
) : ViewModel() {
    fun loginUser(email: String, password: String, onResult: (Response<Boolean>) -> Unit) {
        val loginRequest = LogIn(email, password)
        logInRepository.loginUser(loginRequest) { response ->
            when (response) {
                is Response.Success -> {
                    sharedPreferencesManager.saveUserCredentials(email, password)
                    sharedPreferencesManager.saveLoggedInStatus(true)
                    onResult(Response.Success(true))
                }
                is Response.Error -> {
                    onResult(Response.Error(Exception("Failed to login").toString()))
                }
                else -> Unit
            }
        }
    }

    fun isLoggedIn(): Boolean {
        return sharedPreferencesManager.getUserEmail() != null && sharedPreferencesManager.getUserPassword() != null
    }
}*/
