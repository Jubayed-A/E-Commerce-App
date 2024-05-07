package com.jubayedalam.e_commerce_app.repository.product.category

import com.jubayedalam.e_commerce_app.data.product.category.ProductResponse
import com.jubayedalam.e_commerce_app.network.product.category.ProductCategoryApiService
import com.jubayedalam.e_commerce_app.utils.stateManagement.Response

class ProductCategoryRepository(val apiService: ProductCategoryApiService) {
    suspend fun getCategories(): Response<ProductResponse> {
        return try {
            val response = apiService.getProductCategories()
            if (response.isSuccessful) {
                Response.Success(response.body())
            } else {
                Response.Error("Failed to fetch categories")
            }
        } catch (e: Exception) {
            Response.Error("Error occurred: ${e.message}")
        }
    }

    suspend fun searchCategories(query: String): Response<ProductResponse> {
        return try {
            val response = apiService.searchCategories(query)
            if (response.isSuccessful) {
                Response.Success(response.body())
            } else {
                Response.Error("Failed to search")
            }
        } catch (e: Exception) {
            Response.Error("Network error: ${e.message}")
        }
    }


}