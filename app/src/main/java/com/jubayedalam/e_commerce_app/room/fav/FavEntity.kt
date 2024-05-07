package com.jubayedalam.e_commerce_app.room.fav

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_products")
data class FavEntity (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val productId: String,
    val productName: String,
    val productPrice: String,
    val imageUrl : String
)