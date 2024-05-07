package com.jubayedalam.e_commerce_app.data.profile

data class ProfileResponse(
    val status: String,
    val code : Int,
    val data : ProfileUser,
    val message : String
)
data class ProfileUser(
    val id : Int? = null,
    val first_name: String?= null,
    val last_name: String?= null,
    val email: String?= null,
    val mobile: String?= null,
    val image: String?= null,
    val image_url: String?= null,
    val billing_info: String?= null,
    val billing_info_details: BillingInfoDetails?= null,
    val hide_section: Boolean?= null
)
data class BillingInfoDetails(
    val id: Int?,
    val redex_division_details: Any?, // Change Any to appropriate type if known
    val redex_district_details: Any?, // Change Any to appropriate type if known
    val redex_area_details: Any?,     // Change Any to appropriate type if known
    val created_at: String?,
    val updated_at: String?,
    val first_name: String?,
    val last_name: String?,
    val company_name: String?,
    val country: String?,
    val division: String?,
    val district: String?,
    val street_address: String?,
    val town_city: String?,
    val state_county: String?,
    val postcode_zip: String?,
    val mobile: String?,
    val email: String?,
    val redex_division: Any?, // Change Any to appropriate type if known
    val redex_district: Any?, // Change Any to appropriate type if known
    val redex_area: Any?      // Change Any to appropriate type if known
)
