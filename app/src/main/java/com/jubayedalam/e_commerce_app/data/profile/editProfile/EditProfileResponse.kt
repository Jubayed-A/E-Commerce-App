package com.jubayedalam.e_commerce_app.data.profile.editProfile

data class EditProfileResponse(
    val status: String,
    val code : Int,
    val data : EditProfileUser,
    val message : String
){
    constructor() : this("", 0, EditProfileUser(), "")
}
data class EditProfileUser(
    val id : Int?=null,
    val first_name: String?=null,
    val last_name: String?=null,
    val email: String?=null,
    val mobile: String?=null,
    val image: String?=null,
    val image_url: String?=null,
    val billing_info: Int?=null,
//    val billing_info_details: String?=null,
    val hide_section: Boolean?=null
)