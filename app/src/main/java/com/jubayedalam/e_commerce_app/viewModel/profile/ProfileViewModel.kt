package com.jubayedalam.e_commerce_app.viewModel.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jubayedalam.e_commerce_app.data.profile.ProfileResponse
import com.jubayedalam.e_commerce_app.repository.profile.ProfileRepository
import com.jubayedalam.e_commerce_app.utils.stateManagement.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel (private val userRepository: ProfileRepository) : ViewModel(){
    private val _userLiveData = MutableLiveData<Response<ProfileResponse>>()
    val userLiveData: LiveData<Response<ProfileResponse>> = _userLiveData

    fun getUser(token : String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = userRepository.getProfileUser(token)
            _userLiveData.postValue(response)
        }
    }
}