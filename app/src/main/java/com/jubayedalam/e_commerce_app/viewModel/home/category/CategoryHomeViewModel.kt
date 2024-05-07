package com.jubayedalam.e_commerce_app.viewModel.home.category


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jubayedalam.e_commerce_app.data.home.category.CategoryList
import com.jubayedalam.e_commerce_app.data.home.category.CategoryResponse
import com.jubayedalam.e_commerce_app.repository.home.category.CategoryHomeRepository
import com.jubayedalam.e_commerce_app.utils.stateManagement.Response
import kotlinx.coroutines.launch

class CategoryHomeViewModel(private val repository: CategoryHomeRepository) : ViewModel() {

    private val _categories = MutableLiveData<Response<CategoryResponse>>()
    val categories: LiveData<Response<CategoryResponse>> = _categories

    // LiveData to hold the selected category
    private val _selectedCategory = MutableLiveData<CategoryList>()
    val selectedCategory: LiveData<CategoryList> = _selectedCategory

    // Function to set the selected category
    fun setSelectedCategory(category: CategoryList) {
        _selectedCategory.value = category
    }

    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            _categories.value = Response.Loading()
            _categories.value = repository.getHomeCategories()
        }
    }
}
