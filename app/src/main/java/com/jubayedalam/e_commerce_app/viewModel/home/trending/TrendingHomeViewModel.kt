package com.jubayedalam.e_commerce_app.viewModel.home.trending

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jubayedalam.e_commerce_app.data.home.trending.TrendingResponse
import com.jubayedalam.e_commerce_app.repository.home.trending.TrendingHomeRepository
import com.jubayedalam.e_commerce_app.utils.stateManagement.Response
import kotlinx.coroutines.launch

class TrendingHomeViewModel(private val repository: TrendingHomeRepository) : ViewModel() {

    private val _trendingHomeProduct = MutableLiveData<Response<TrendingResponse>>()
    val trendingHomeProduct: LiveData<Response<TrendingResponse>> = _trendingHomeProduct
    private val _trendingHomeProductNext = MutableLiveData<Response<TrendingResponse>>()

    init {
        fetchTrendingHomeProducts()
    }

    private fun fetchTrendingHomeProducts() {

        viewModelScope.launch {
            _trendingHomeProduct.value = Response.Loading()
            _trendingHomeProduct.value = repository.getTrendingHomeProducts()
        }
    }

    // this is for showing product based on category
    fun filterTrendingProductsByCategory(categoryId: Int) {
        viewModelScope.launch {
            _trendingHomeProduct.value = Response.Loading()
            _trendingHomeProduct.value = repository.getTrendingProductsByCategory(categoryId)
        }
    }

    // this is for paggaing
    /*     private fun getNextTrendingProduct(url : String): MutableLiveData<Response<TrendingResponse>> {
            viewModelScope.launch {
                 _trendingHomeProductNext.value = repository.getTrendingHomeProducts()
            }
            return _trendingHomeProductNext
        }*/

}