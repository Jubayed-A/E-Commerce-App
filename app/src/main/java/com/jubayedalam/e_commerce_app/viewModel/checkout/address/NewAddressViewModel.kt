package com.jubayedalam.e_commerce_app.viewModel.checkout.address

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jubayedalam.e_commerce_app.data.checkout.address.NewAddress
import com.jubayedalam.e_commerce_app.data.checkout.address.NewAddressResponseData
import com.jubayedalam.e_commerce_app.repository.checkout.address.NewAddressRepository
import com.jubayedalam.e_commerce_app.utils.stateManagement.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewAddressViewModel (private val repository: NewAddressRepository): ViewModel() {
    private val _response = MutableLiveData<Response<NewAddressResponseData>>()
    val response: LiveData<Response<NewAddressResponseData>> = _response

    fun setNewAddress(newAddress: NewAddress) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.setNewAddress(newAddress)
            _response.postValue(result)
        }
    }

}