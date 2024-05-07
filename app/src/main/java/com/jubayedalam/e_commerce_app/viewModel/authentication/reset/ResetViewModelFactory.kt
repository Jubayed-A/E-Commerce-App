package com.jubayedalam.e_commerce_app.viewModel.authentication.reset

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jubayedalam.e_commerce_app.network.authentication.reset.ResetRetrofitInstance
import com.jubayedalam.e_commerce_app.repository.authentication.reset.ResetRepository

class ResetViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResetViewModel::class.java)) {
            val resetRepository = ResetRepository(ResetRetrofitInstance.apiService)
            return ResetViewModel(resetRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}