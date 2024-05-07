package com.jubayedalam.e_commerce_app.repository.category

import com.jubayedalam.e_commerce_app.data.category.Category
import com.jubayedalam.e_commerce_app.network.category.CategoryApiService
import com.jubayedalam.e_commerce_app.utils.stateManagement.Response

class CategoryRepository(private val apiService: CategoryApiService) {
    suspend fun getCategories(): Response<Category> {
        return try {
            val response = apiService.getCategories()
            if (response.isSuccessful) {
                Response.Success(response.body())
            } else {
                Response.Error("Failed to fetch categories")
            }
        } catch (e: Exception) {
            Response.Error("Error occurred: ${e.message}")
        }
    }
}