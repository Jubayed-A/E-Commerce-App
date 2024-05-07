package com.jubayedalam.e_commerce_app.viewModel.checkout.placeholder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jubayedalam.e_commerce_app.data.checkout.placeOrder.CheckoutRequest
import com.jubayedalam.e_commerce_app.data.checkout.placeOrder.CheckoutResponse
import com.jubayedalam.e_commerce_app.repository.checkout.placeholder.CheckoutRepository
import com.jubayedalam.e_commerce_app.utils.stateManagement.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CheckoutViewModel(private val repository: CheckoutRepository) : ViewModel() {
    private val _response = MutableLiveData<Response<CheckoutResponse>>()
    val response: LiveData<Response<CheckoutResponse>> = _response

    fun setNewAddress(checkout: CheckoutRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.setCheckout(checkout)
            _response.postValue(result)
        }
    }
}