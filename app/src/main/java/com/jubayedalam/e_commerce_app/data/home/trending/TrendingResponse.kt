package com.jubayedalam.e_commerce_app.data.home.trending

import com.google.gson.annotations.SerializedName

data class TrendingResponse(
    val status: String,
    val code: Int,
    @SerializedName("max_price") val maxPrice: Int,
    @SerializedName("min_price") val minPrice: Int,
    val data: TrendingData,
    val message: String?
)
data class TrendingData(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: ArrayList<Product>,
    @SerializedName("is_combo_category") val isComboCategory: Boolean
)
data class Product(
    val id: Int,
    val name: String,
    val slug: String? = null,
    val sku: String? = null,
    @SerializedName("category_name") val categoryName: String? = null,
    @SerializedName("category_slug") val categorySlug: String? = null,
    @SerializedName("thumb_url") val thumbUrl: String,
    @SerializedName("thumb_url2") val thumbUrl2: String? = null,
    val price: String,
    @SerializedName("offer_price") val offerPrice: String? = null,
    val discount: Int? = null,
    @SerializedName("discount_type_text") val discountTypeText: String? = null,
    val description: String? = null,
    @SerializedName("is_new_arrival") val isNewArrival: Boolean? = null,
    @SerializedName("has_offer") val hasOffer: Boolean? = null,
    @SerializedName("has_combo_offer") val hasComboOffer: Boolean? = null,
    @SerializedName("discount_flat_amount") val discountFlatAmount: Int? = null
)