package com.jubayedalam.e_commerce_app.viewModel.checkout.address

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jubayedalam.e_commerce_app.network.checkout.address.NewAddressRetrofitInstance
import com.jubayedalam.e_commerce_app.repository.checkout.address.NewAddressRepository

class NewAddressViewModelFactory(private val repository: NewAddressRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewAddressViewModel::class.java)) {
            val repository = NewAddressRepository(NewAddressRetrofitInstance.newAddressApiService)
            return NewAddressViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}