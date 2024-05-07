package com.jubayedalam.e_commerce_app.repository.product.item


import com.jubayedalam.e_commerce_app.data.product.item.ProductItemResponse
import com.jubayedalam.e_commerce_app.network.product.item.ProductItemApiServices
import com.jubayedalam.e_commerce_app.utils.stateManagement.Response

class ProductItemRepository (private val apiServices: ProductItemApiServices){

    suspend fun getProductsItems(): Response<ProductItemResponse> {
        return try {
            val response = apiServices.getAllProducts()
            if (response.isSuccessful) {
                Response.Success(response.body())
            } else {
                Response.Error("Failed to fetch productItem")
            }
        } catch (e: Exception) {
            Response.Error("Error occurred: ${e.message}")
        }
    }

    suspend fun getProductsItemsByCategory(categoryId: Int): Response<ProductItemResponse> {
        return try {
            val response = apiServices.getProductItemByCategory(categoryId)
            if (response.isSuccessful) {
                Response.Success(response.body())
            } else {
                Response.Error("Failed to fetch productItem by category")
            }
        } catch (e: Exception) {
            Response.Error("Error occurred: ${e.message}")
        }
    }


}