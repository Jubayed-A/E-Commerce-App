package com.jubayedalam.e_commerce_app.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
data class CartEntity (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val productId: String,
    val productName: String,
    val productPrice: String,
    val imageUrl : String,
    var quantity : Int
)