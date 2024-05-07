package com.jubayedalam.e_commerce_app.repository.product.fav

import com.jubayedalam.e_commerce_app.room.fav.FavDao
import com.jubayedalam.e_commerce_app.room.fav.FavEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavProductRepository(private val dao: FavDao) {
    suspend fun getAllProducts(): List<FavEntity> {
        return withContext(Dispatchers.IO) {
            dao.getAllFavProducts()
        }
    }

    suspend fun insertProduct(product: FavEntity) {
        withContext(Dispatchers.IO) {
            dao.insertFavProduct(product)
        }
    }

    suspend fun updateProduct(product: FavEntity) {
        withContext(Dispatchers.IO){
            dao.updateFavProduct(product)
        }
    }

    suspend fun deleteProduct(product: FavEntity) {
        withContext(Dispatchers.IO) {
            dao.deleteFavProduct(product)
        }
    }
}