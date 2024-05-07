package com.jubayedalam.e_commerce_app.viewModel.checkout.addressGet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jubayedalam.e_commerce_app.network.checkout.addressGet.AddressRetrofitInstance
import com.jubayedalam.e_commerce_app.repository.checkout.addressGet.AddressRepository

class AddressViewModelFactory(private val repository: AddressRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddressViewModel::class.java)) {
            val repository = AddressRepository(AddressRetrofitInstance.addressApiService)
            return AddressViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}