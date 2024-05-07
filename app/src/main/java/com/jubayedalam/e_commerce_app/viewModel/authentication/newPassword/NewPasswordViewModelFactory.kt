package com.jubayedalam.e_commerce_app.viewModel.authentication.newPassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jubayedalam.e_commerce_app.repository.authentication.newPassword.NewPasswordRepository

class NewPasswordViewModelFactory(private val repository: NewPasswordRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewPasswordViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NewPasswordViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}