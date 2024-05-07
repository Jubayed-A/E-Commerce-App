package com.jubayedalam.e_commerce_app.data.checkout.placeOrder

data class CheckoutResponse(
    val status: String,
    val code: Int,
    val data: CheckoutRequest,
    val message: String?,
)

data class CheckoutRequest(
    val cart: CartItem,
    val password: String,
    val status: Int,
    val paymentType: Int,
    val shippingType: Int,
    val deliveryChargeType: Int,
    val deliveryCharge: String,
    val subTotal: String,
    val totalDue: String,
    val totalPaid: String,
    val totalAmount: String,
    val totalDiscount: String,
    val createAccount: Boolean,
    val shipToDifferentAddress: Boolean,
    val shippingFirstName: String,
    val shippingLastName: String,
    val shippingCompanyName: String,
    val shippingCountry: String,
    val shippingDivision: String,
    val shippingDistrict: String,
    val shippingTownCity: String,
    val shippingStreetAddress: String,
    val shippingStateCounty: String,
    val shippingPostcodeZip: String,
    val shippingMobile: String,
    val shippingEmail: String,
    val orderNotes: String,
    val successUrl: String,
    val sslStatus: String,
    val coupon: Int,
    val redexDivision: Int,
    val redexDistrict: Int,
    val redexArea: Int
)

data class CartItem(
    val item: List<Item>
)

data class Item(
    val quantity: Int,
    val product: Int,
    val variant: Int
)

