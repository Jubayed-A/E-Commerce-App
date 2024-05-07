package com.jubayedalam.e_commerce_app.repository.home.category

import com.jubayedalam.e_commerce_app.data.home.category.CategoryResponse
import com.jubayedalam.e_commerce_app.network.home.category.CategoryHomeApiService
import com.jubayedalam.e_commerce_app.utils.stateManagement.Response

class CategoryHomeRepository(private val apiService: CategoryHomeApiService) {

    suspend fun getHomeCategories(): Response<CategoryResponse> {
        return try {
            val response = apiService.getHomeCategories()
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
