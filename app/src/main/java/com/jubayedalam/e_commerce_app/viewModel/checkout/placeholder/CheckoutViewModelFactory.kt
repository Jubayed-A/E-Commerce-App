package com.jubayedalam.e_commerce_app.viewModel.checkout.placeholder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jubayedalam.e_commerce_app.network.checkout.placeOrder.CheckoutRetrofitInstance
import com.jubayedalam.e_commerce_app.repository.checkout.placeholder.CheckoutRepository

class CheckoutViewModelFactory(private var repository: CheckoutRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CheckoutViewModel::class.java)) {
            repository = CheckoutRepository(CheckoutRetrofitInstance.checkoutApiService)
            return CheckoutViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}