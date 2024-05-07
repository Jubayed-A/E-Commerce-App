package com.jubayedalam.e_commerce_app.viewModel.product.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jubayedalam.e_commerce_app.repository.product.category.ProductCategoryRepository

class ProductCategoryViewModelFactory(private val repository: ProductCategoryRepository) : ViewModelProvider.Factory  {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductCategoryViewModel::class.java)) {
            return ProductCategoryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}