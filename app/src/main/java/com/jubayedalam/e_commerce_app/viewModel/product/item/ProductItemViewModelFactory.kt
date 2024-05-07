package com.jubayedalam.e_commerce_app.viewModel.product.item

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jubayedalam.e_commerce_app.repository.product.item.ProductItemRepository

class ProductItemViewModelFactory(private val repository: ProductItemRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductItemViewModel::class.java)) {
            return ProductItemViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
