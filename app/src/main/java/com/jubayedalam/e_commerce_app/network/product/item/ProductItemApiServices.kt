package com.jubayedalam.e_commerce_app.network.product.item

import com.jubayedalam.e_commerce_app.data.product.item.ProductItemResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductItemApiServices {
    @GET("api/v1/inventory/web/product/?limit=30")
    suspend fun getAllProducts() : Response<ProductItemResponse>

    @GET("api/v1/inventory/web/product/?limit=20")
    suspend fun getProductItemByCategory(@Query("category") categoryId: Int): Response<ProductItemResponse>
}