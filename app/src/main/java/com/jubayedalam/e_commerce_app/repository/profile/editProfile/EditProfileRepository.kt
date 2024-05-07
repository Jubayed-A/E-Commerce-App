package com.jubayedalam.e_commerce_app.repository.profile.editProfile

import android.net.Uri
import android.util.Log
import com.jubayedalam.e_commerce_app.data.profile.editProfile.EditProfileResponse
import com.jubayedalam.e_commerce_app.network.profile.editProfile.EditProfileApiService
import com.jubayedalam.e_commerce_app.utils.stateManagement.Response2
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class EditProfileRepository(private val apiService: EditProfileApiService) {

    suspend fun updateUserInfo(
        id: Int,
        firstName: String,
        lastName: String,
        email: String,
        mobile: String? = null,
        billingInfo: Int? = null,
//        billingInfoDetails: String? = null,
        hideSection: Boolean? = null,
        imageUri: Uri? = null,
        token: String
    ): Response2<EditProfileResponse> {
        return try {
            val idRequestBody = createPartFromString(id.toString())
            val firstNameRequestBody = createPartFromString(firstName)
            val lastNameRequestBody = createPartFromString(lastName)
            val emailRequestBody = createPartFromString(email)
            val mobileRequestBody = mobile?.let { createPartFromString(it) }
                ?: RequestBody.create(MediaType.parse("text/plain"), "")
            val billingInfoRequestBody = billingInfo?.let { createPartFromString(it.toString()) }
                ?: RequestBody.create(MediaType.parse("text/plain"), "")
//            val billingInfoDetailsRequestBody = billingInfoDetails?.let { createPartFromString(it) }
//                ?: RequestBody.create(MediaType.parse("text/plain"), "")
            val hideSectionRequestBody = hideSection?.let { createPartFromString(it.toString()) }
                ?: RequestBody.create(MediaType.parse("text/plain"), "")
            val imagePart = imageUri?.let { createImagePart(it) }



            Log.d("EditProfileRepository ", imageUri.toString())


            Log.d("EditProfileRepository ", imagePart.toString())


            val response = apiService.updateUserInfo(
                idRequestBody,
                firstNameRequestBody,
                lastNameRequestBody,
                emailRequestBody,
                mobileRequestBody,
                billingInfoRequestBody,
//                billingInfoDetailsRequestBody,
                hideSectionRequestBody,
                imagePart,
                token
            )

            // Check if the response is not null and is not an error response
            if (response.body() != null && response.errorBody() == null) {
                Response2.Success(response.body())
            } else {
                Response2.Error(response.message() ?: "Unknown error")
            }
        } catch (e: Exception) {
            Response2.Error(e.message ?: "Network error")
        }
    }

    private fun createPartFromString(value: String): RequestBody {
        return RequestBody.create(MediaType.parse("text/plain"), value)
    }

    private fun createImagePart(imageUri: Uri): MultipartBody.Part {
        val file = File(imageUri.path ?: "")
        val requestFile = RequestBody.create(MediaType.parse("image/*"), file)
        return MultipartBody.Part.createFormData("image", file.name, requestFile)
    }
}
