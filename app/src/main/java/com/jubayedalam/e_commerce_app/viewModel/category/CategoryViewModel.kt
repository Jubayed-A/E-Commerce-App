package com.jubayedalam.e_commerce_app.viewModel.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jubayedalam.e_commerce_app.data.category.Category
import com.jubayedalam.e_commerce_app.repository.category.CategoryRepository
import com.jubayedalam.e_commerce_app.utils.stateManagement.Response
import kotlinx.coroutines.launch

class CategoryViewModel (private val repository: CategoryRepository): ViewModel() {
    private val _categories = MutableLiveData<Response<Category>>()
    val categories: LiveData<Response<Category>> = _categories

    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            _categories.value = Response.Loading()
            _categories.value = repository.getCategories()
        }
    }
}