package com.jubayedalam.e_commerce_app.data.checkout.address

data class NewAddressResponseData(
    val status: String,
    val code: Int,
    val data: NewAddressResponseData?,
    val message: String?
)
data class NewAddress(
    val idn: Int,
    val first_name: String,
    val last_name: String,
    val company_name: String,
    val country: String,
    val division: String,
    val district: String,
    val street_address: String,
    val town_city: String,
    val state_county: String,
    val postcode_zip: String,
    val mobile: String,
    val email: String
)
