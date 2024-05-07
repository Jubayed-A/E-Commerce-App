package com.jubayedalam.e_commerce_app.viewModel.home.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jubayedalam.e_commerce_app.repository.home.category.CategoryHomeRepository

class CategoryHomeViewModelFactory(private val repository: CategoryHomeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoryHomeViewModel::class.java)) {
            return CategoryHomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}