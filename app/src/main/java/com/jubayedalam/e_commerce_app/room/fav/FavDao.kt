package com.jubayedalam.e_commerce_app.room.fav

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface FavDao {
    @Query("SELECT * FROM fav_products")
    fun getAllFavProducts(): List<FavEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavProduct(product: FavEntity)

    @Update
    suspend fun updateFavProduct(product: FavEntity)

    @Delete
    suspend fun deleteFavProduct(product: FavEntity)
}