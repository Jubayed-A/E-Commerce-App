package com.jubayedalam.e_commerce_app.network.category

import com.jubayedalam.e_commerce_app.data.category.Category
import retrofit2.Response
import retrofit2.http.GET

interface CategoryApiService {
    @GET("api/v1/inventory/web/category/?category_type=2")
    suspend fun getCategories(): Response<Category>
}