package com.jubayedalam.e_commerce_app.network.home.category

import com.jubayedalam.e_commerce_app.data.home.category.CategoryResponse
import retrofit2.Response
import retrofit2.http.GET

interface CategoryHomeApiService {
    //    @GET("api/v1/inventory/web/category/?category_type=1&main_category=0")
    @GET("api/v1/inventory/web/category/?category_type=2")
    suspend fun getHomeCategories(): Response<CategoryResponse>
}