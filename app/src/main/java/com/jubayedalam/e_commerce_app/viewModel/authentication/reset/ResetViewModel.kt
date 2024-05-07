/*package com.devsstore.devsstore.viewModel.authentication.reset

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devsstore.devsstore.data.authentication.reset.ResetPassword
import com.devsstore.devsstore.data.authentication.reset.ResetResponse
import com.devsstore.devsstore.network.authentication.reset.ResetApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ResetViewModel(private val apiService: ResetApiService) : ViewModel() {

    private val _resetResponse = MutableLiveData<ResetResponse?>()
    val resetResponse: LiveData<ResetResponse?>
        get() = _resetResponse

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun sendUserEmail(email: String) {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiService.sendUserEmail(ResetPassword(email))
                if (response.isSuccessful) {
                    val resetResponse = response.body()
                    if (resetResponse != null) {
                        _resetResponse.postValue(resetResponse)
                    } else {
                        // Handle empty response
                    }
                } else {
                    _resetResponse.postValue(ResetResponse(status = "error", code = response.code(), data = null, message = null, errors = null))
                }
            } catch (e: Exception) {
                // Handle network or unexpected errors
                _resetResponse.postValue(ResetResponse(status = "error", code = -1, data = null, message = e.message, errors = null))
            } finally {
                _isLoading.postValue(false)
            }
        }
    }
}*/


package com.jubayedalam.e_commerce_app.viewModel.authentication.reset

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jubayedalam.e_commerce_app.data.authentication.reset.ResetPassword
import com.jubayedalam.e_commerce_app.data.authentication.reset.ResetResponse
import com.jubayedalam.e_commerce_app.repository.authentication.reset.ResetRepository
import com.jubayedalam.e_commerce_app.utils.stateManagement.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ResetViewModel(private val resetRepository: ResetRepository) : ViewModel() {

    private val _resetResponse = MutableLiveData<Response<ResetResponse>>()
    val resetResponse: LiveData<Response<ResetResponse>>
        get() = _resetResponse

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun sendUserEmail(email: String) {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val response = resetRepository.sendUserEmail(ResetPassword(email))
            _resetResponse.postValue(response)
            _isLoading.postValue(false)
        }
    }
}
