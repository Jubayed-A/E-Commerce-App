package com.jubayedalam.e_commerce_app.viewModel.checkout.addressGet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jubayedalam.e_commerce_app.data.checkout.addressGet.Address
import com.jubayedalam.e_commerce_app.repository.checkout.addressGet.AddressRepository
import com.jubayedalam.e_commerce_app.utils.stateManagement.Response
import kotlinx.coroutines.launch

class AddressViewModel(private val repository: AddressRepository) : ViewModel() {
    private val _addresses = MutableLiveData<Response<List<Address>>>()
    val addresses: LiveData<Response<List<Address>>> = _addresses

    fun getAllAddresses() {
        viewModelScope.launch {
            _addresses.value = Response.Loading()
            _addresses.value = repository.getAllAddresses()
        }
    }
}
