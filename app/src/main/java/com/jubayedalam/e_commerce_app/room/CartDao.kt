package com.jubayedalam.e_commerce_app.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface CartDao {

    @Query("SELECT * FROM cart_items")
    fun getAllProducts(): List<CartEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProduct(product: CartEntity)

    @Update
    suspend fun updateProduct(product: CartEntity)

    @Delete
    suspend fun deleteProduct(product: CartEntity)

}
