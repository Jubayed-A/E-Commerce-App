package com.jubayedalam.e_commerce_app.data.product.category

data class ProductResponse(
    val status: String,
    val code: Int,
    val max_price: Int,
    val min_price: Int,
    val data: Data
)
data class Data(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: ArrayList<ProductCategoryList>,
    val is_combo_category: Boolean
)

data class ProductCategoryList(
    val id: Int,
    val name: String,
    val slug: String,
    val has_categories: Boolean,
    val thumb_url: String,
    val main_category: Int,
    val sub_main_category: Int?,
    var isSelected: Boolean = false
)

