package com.jubayedalam.e_commerce_app.viewModel.profile.editProfile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jubayedalam.e_commerce_app.repository.profile.editProfile.EditProfileRepository

class EditProfileViewModelFactory(private val repository: EditProfileRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditProfileViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EditProfileViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
