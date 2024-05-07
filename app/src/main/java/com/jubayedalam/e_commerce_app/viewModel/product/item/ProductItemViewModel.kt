package com.jubayedalam.e_commerce_app.viewModel.product.item

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jubayedalam.e_commerce_app.data.product.item.ProductItemResponse
import com.jubayedalam.e_commerce_app.repository.product.item.ProductItemRepository
import com.jubayedalam.e_commerce_app.utils.stateManagement.Response
import kotlinx.coroutines.launch

class ProductItemViewModel(private val repository: ProductItemRepository) : ViewModel() {

    private val _productItem = MutableLiveData<Response<ProductItemResponse>>()
    val productItem: LiveData<Response<ProductItemResponse>> = _productItem

    init {
        fetchProductItems()
    }

    private fun fetchProductItems() {
        viewModelScope.launch {
            _productItem.value = Response.Loading()
            _productItem.value = repository.getProductsItems()
        }
    }

    fun filterProductItemsByCategory(categoryId: Int) {
        viewModelScope.launch {
            _productItem.value = Response.Loading()
            _productItem.value = repository.getProductsItemsByCategory(categoryId)
        }
    }

}

