package com.jubayedalam.e_commerce_app.viewModel.cart

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jubayedalam.e_commerce_app.repository.cart.CartRepository
import com.jubayedalam.e_commerce_app.room.CartDB
import com.jubayedalam.e_commerce_app.room.CartEntity
import com.jubayedalam.e_commerce_app.utils.stateManagement.Response
import kotlinx.coroutines.launch

/*
class CartViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CartRepository
    val allProducts: LiveData<List<CartEntity>>

    init {
        val dao = CartDB.getDatabase(application).productDao()
        repository = CartRepository(dao)
        allProducts = repository.getAllProducts()
    }

    fun insertProduct(product: CartEntity) {
        viewModelScope.launch {
            repository.insertProduct(product)
        }
    }

    fun deleteProduct(product: CartEntity) {
        viewModelScope.launch {
            repository.deleteProduct(product)
        }
    }


}
*/

class CartViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CartRepository

    private val _cartResponse = MutableLiveData<Response<List<CartEntity>>>()
    val cartResponse: LiveData<Response<List<CartEntity>>> = _cartResponse

    init {
        val dao = CartDB.getDatabase(application).productDao()
        repository = CartRepository(dao)
        getAllProducts()
    }

     private fun getAllProducts() {
        viewModelScope.launch {
            _cartResponse.value = Response.Loading()
            try {
                val products = repository.getAllProducts()
                _cartResponse.value = Response.Success(products)
            } catch (e: Exception) {
                _cartResponse.value = Response.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun insertProduct(product: CartEntity) {
        viewModelScope.launch {
            repository.insertProduct(product)
        }
    }

    fun updateProduct(product: CartEntity) {
        viewModelScope.launch {
            repository.updateProduct(product)
        }
    }

    fun deleteProduct(product: CartEntity) {
        viewModelScope.launch {
            repository.deleteProduct(product)
            // Update LiveData after deletion
            getAllProducts()
        }
    }
}


