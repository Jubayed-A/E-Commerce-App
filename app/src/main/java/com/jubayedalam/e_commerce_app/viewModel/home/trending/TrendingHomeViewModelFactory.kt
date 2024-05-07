package com.jubayedalam.e_commerce_app.viewModel.home.trending

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jubayedalam.e_commerce_app.repository.home.trending.TrendingHomeRepository

class TrendingHomeViewModelFactory (private val repository: TrendingHomeRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TrendingHomeViewModel::class.java)) {
            return TrendingHomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}