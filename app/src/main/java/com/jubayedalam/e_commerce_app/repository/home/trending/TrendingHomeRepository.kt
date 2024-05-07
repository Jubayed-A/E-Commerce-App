package com.jubayedalam.e_commerce_app.repository.home.trending

import com.jubayedalam.e_commerce_app.data.home.trending.TrendingResponse
import com.jubayedalam.e_commerce_app.network.home.trending.TrendingHomeApiService
import com.jubayedalam.e_commerce_app.utils.stateManagement.Response

class TrendingHomeRepository(private val apiService: TrendingHomeApiService) {
    suspend fun getTrendingHomeProducts(): Response<TrendingResponse> {
        return try {
            val response = apiService.getTrendingHomeProducts()
            if (response.isSuccessful) {
                Response.Success(response.body())
            } else {
                Response.Error("Failed to fetch trendingProducts")
            }
        } catch (e: Exception) {
            Response.Error("Error occurred: ${e.message}")
        }
    }

    suspend fun getTrendingProductsByCategory(categoryId: Int): Response<TrendingResponse> {
        return try {
            val response = apiService.getTrendingProductsByCategory(categoryId)
            if (response.isSuccessful) {
                Response.Success(response.body())
            } else {
                Response.Error("Failed to fetch trendingProducts by category")
            }
        } catch (e: Exception) {
            Response.Error("Error occurred: ${e.message}")
        }
    }

}