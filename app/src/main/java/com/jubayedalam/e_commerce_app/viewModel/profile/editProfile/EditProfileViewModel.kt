package com.jubayedalam.e_commerce_app.viewModel.profile.editProfile

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jubayedalam.e_commerce_app.data.profile.editProfile.EditProfileResponse
import com.jubayedalam.e_commerce_app.repository.profile.editProfile.EditProfileRepository
import com.jubayedalam.e_commerce_app.utils.stateManagement.Response2
import kotlinx.coroutines.launch

class EditProfileViewModel(private val repository: EditProfileRepository) : ViewModel() {
    private val _editProfileResponseLiveData = MutableLiveData<Response2<EditProfileResponse>>()
    val editProfileResponseLiveData: LiveData<Response2<EditProfileResponse>> = _editProfileResponseLiveData

    fun updateUserInfo(
        id: Int,
        firstName: String,
        lastName: String,
        email: String,
        mobile: String?,
        billingInfo: Int?,
//        billingInfoDetails: String?,
        hideSection: Boolean?,
        imageUri: Uri?,
        token: String
    ) {
        viewModelScope.launch {
            val response = repository.updateUserInfo(
                id, firstName, lastName, email, mobile, billingInfo,  hideSection, imageUri, token
            )
            _editProfileResponseLiveData.value = response
        }
    }
}
