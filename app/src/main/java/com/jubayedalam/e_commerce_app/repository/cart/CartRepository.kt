package com.jubayedalam.e_commerce_app.repository.cart

import com.jubayedalam.e_commerce_app.room.CartDao
import com.jubayedalam.e_commerce_app.room.CartEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CartRepository(private val dao: CartDao) {

    suspend fun getAllProducts(): List<CartEntity> {
        return withContext(Dispatchers.IO) {
            dao.getAllProducts()
        }
    }

    suspend fun insertProduct(product: CartEntity) {
        withContext(Dispatchers.IO) {
            dao.insertProduct(product)
        }
    }

    suspend fun updateProduct(product: CartEntity) {
        withContext(Dispatchers.IO){
            dao.updateProduct(product)
        }
    }

    suspend fun deleteProduct(product: CartEntity) {
        withContext(Dispatchers.IO) {
            dao.deleteProduct(product)
        }
    }
}
