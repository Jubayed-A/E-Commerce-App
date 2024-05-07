package com.jubayedalam.e_commerce_app.viewModel.product.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jubayedalam.e_commerce_app.data.product.category.ProductResponse
import com.jubayedalam.e_commerce_app.repository.product.category.ProductCategoryRepository
import com.jubayedalam.e_commerce_app.utils.stateManagement.Response
import kotlinx.coroutines.launch

class ProductCategoryViewModel(private val repository: ProductCategoryRepository) : ViewModel(){

    private val _productCategories = MutableLiveData<Response<ProductResponse>>()
    val productCategories: LiveData<Response<ProductResponse>> = _productCategories

    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            _productCategories.value = Response.Loading()
            _productCategories.value = repository.getCategories()
        }
    }

    fun searchCategory(query: String) {
        viewModelScope.launch {
            _productCategories.value = Response.Loading()
            _productCategories.value = repository.searchCategories(query)
        }
    }

}