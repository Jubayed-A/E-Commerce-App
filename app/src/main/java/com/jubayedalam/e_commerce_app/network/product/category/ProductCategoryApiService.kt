package com.jubayedalam.e_commerce_app.network.product.category

import com.jubayedalam.e_commerce_app.data.product.category.ProductResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductCategoryApiService {
    @GET("api/v1/inventory/web/category/?category_type=2")
    suspend fun getProductCategories(): Response<ProductResponse>

    @GET("api/v1/inventory/web/category/")
    suspend fun searchCategories(@Query("search") query: String): Response<ProductResponse>

}