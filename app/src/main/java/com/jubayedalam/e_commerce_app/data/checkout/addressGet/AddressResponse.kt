package com.jubayedalam.e_commerce_app.data.checkout.addressGet

import android.os.Parcel
import android.os.Parcelable

data class AddressResponse(
    val status: String,
    val code: Int,
    val data: AddressData,
    val message: String?
)

data class AddressData(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Address>
)

data class Address(
    val id: Int = 0,
    val created_at: String = "",
    val updated_at: String = "",
    val first_name: String = "",
    val last_name: String = "",
    val company_name: String = "",
    val country: String = "",
    val division: String = "",
    val district: String = "",
    val street_address: String = "",
    val town_city: String = "",
    val state_county: String = "",
    val postcode_zip: String = "",
    val mobile: String = "",
    val email: String = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(created_at)
        parcel.writeString(updated_at)
        parcel.writeString(first_name)
        parcel.writeString(last_name)
        parcel.writeString(company_name)
        parcel.writeString(country)
        parcel.writeString(division)
        parcel.writeString(district)
        parcel.writeString(street_address)
        parcel.writeString(town_city)
        parcel.writeString(state_county)
        parcel.writeString(postcode_zip)
        parcel.writeString(mobile)
        parcel.writeString(email)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Address> {
        override fun createFromParcel(parcel: Parcel): Address {
            return Address(parcel)
        }

        override fun newArray(size: Int): Array<Address?> {
            return arrayOfNulls(size)
        }
    }
}
