package com.jubayedalam.e_commerce_app.network.home.trending

import com.jubayedalam.e_commerce_app.data.home.trending.TrendingResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TrendingHomeApiService {
    @GET("api/v1/inventory/web/product/?sort_by=5")
    suspend fun getTrendingHomeProducts(): Response<TrendingResponse>

    @GET("api/v1/inventory/web/product/?sort_by=5")
    suspend fun getTrendingProductsByCategory(@Query("category") categoryId: Int): Response<TrendingResponse>
}
