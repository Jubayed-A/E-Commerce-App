package com.jubayedalam.e_commerce_app.data.product.item

import com.google.gson.annotations.SerializedName

data class ProductItemResponse  (
    val status: String,
    val code: Int,
    @SerializedName("max_price") val maxPrice: Double,
    @SerializedName("min_price") val minPrice: Double,
    val data: ProductItemData?,
    val message: String?
)
data class ProductItemData(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: ArrayList<ProductItem>
)

data class ProductItem(
    val id: Int,
    val name: String,
    val slug: String,
    val sku: String,
    @SerializedName("category_name") val categoryName: String,
    @SerializedName("category_slug") val categorySlug: String,
    @SerializedName("thumb_url") val thumbUrl: String,
    @SerializedName("thumb_url2") val thumbUrl2: String,
    val price: String,
    @SerializedName("offer_price") val offerPrice: String,
    val discount: Int,
    @SerializedName("discount_type_text") val discountTypeText: String,
    val description: String,
    @SerializedName("is_new_arrival") val isNewArrival: Boolean,
    @SerializedName("has_offer") val hasOffer: Boolean,
    @SerializedName("has_combo_offer") val hasComboOffer: Boolean,
    @SerializedName("discount_flat_amount") val discountFlatAmount: Double
)