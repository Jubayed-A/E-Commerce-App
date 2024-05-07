package com.jubayedalam.e_commerce_app.data.category

data class Category(
    val status: String,
    val code: Int,
    val max_price: Int,
    val min_price: Int,
    val data: CategoryData
)
data class CategoryData(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: ArrayList<CategoryItems>,
    val is_combo_category: Boolean
)
data class CategoryItems(
    val id: Int,
    val name: String,
    val slug: String,
    val has_categories: Boolean,
    val thumb_url: String,
    val main_category: Int,
    val sub_main_category: Int?,
    var isSelected: Boolean = false
)