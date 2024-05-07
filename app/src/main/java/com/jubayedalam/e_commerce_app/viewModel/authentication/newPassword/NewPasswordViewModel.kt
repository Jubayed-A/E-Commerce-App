package com.jubayedalam.e_commerce_app.viewModel.authentication.newPassword

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jubayedalam.e_commerce_app.data.authentication.newPassword.NewPassword
import com.jubayedalam.e_commerce_app.data.authentication.newPassword.NewPasswordResponse
import com.jubayedalam.e_commerce_app.repository.authentication.newPassword.NewPasswordRepository
import com.jubayedalam.e_commerce_app.utils.stateManagement.Response
import kotlinx.coroutines.launch

class NewPasswordViewModel(private val repository: NewPasswordRepository) : ViewModel() {

    private val _newPasswordResponse = MutableLiveData<Response<NewPasswordResponse>>()
    val newPasswordResponse: LiveData<Response<NewPasswordResponse>>
        get() = _newPasswordResponse

    fun setNewPassword(email: String, code: String, password: String) {
        viewModelScope.launch {
            val newPassword = NewPassword(email, code, password)
            _newPasswordResponse.value = repository.setNewPassword(newPassword)
        }
    }
}
