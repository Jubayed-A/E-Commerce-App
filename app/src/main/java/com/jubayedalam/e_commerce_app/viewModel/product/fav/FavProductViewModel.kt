package com.jubayedalam.e_commerce_app.viewModel.product.fav

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jubayedalam.e_commerce_app.repository.product.fav.FavProductRepository
import com.jubayedalam.e_commerce_app.room.fav.FavDB
import com.jubayedalam.e_commerce_app.room.fav.FavEntity
import com.jubayedalam.e_commerce_app.utils.stateManagement.Response
import kotlinx.coroutines.launch

class FavProductViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: FavProductRepository

    private val _favProductResponse = MutableLiveData<Response<List<FavEntity>>>()
    val favProductResponse: LiveData<Response<List<FavEntity>>> = _favProductResponse

    init {
        val dao = FavDB.getDatabase(application).favDao()
        repository = FavProductRepository(dao)
        getAllProducts()
    }

    fun getAllProducts() {
        viewModelScope.launch {
            _favProductResponse.value = Response.Loading()
            try {
                val products = repository.getAllProducts()
                _favProductResponse.value = Response.Success(products)
            } catch (e: Exception) {
                _favProductResponse.value = Response.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun insertProduct(product: FavEntity) {
        viewModelScope.launch {
            repository.insertProduct(product)
        }
    }

    fun updateProduct(product: FavEntity) {
        viewModelScope.launch {
            repository.updateProduct(product)
        }
    }

    fun deleteProduct(product: FavEntity) {
        viewModelScope.launch {
            repository.deleteProduct(product)
            // Update LiveData after deletion
            getAllProducts()
        }
    }
}