package com.jubayedalam.e_commerce_app.ui.activity.profile

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.github.dhaval2404.imagepicker.ImagePicker
import com.jubayedalam.e_commerce_app.data.profile.editProfile.EditProfileResponse
import com.jubayedalam.e_commerce_app.data.profile.editProfile.EditProfileUser
import com.jubayedalam.e_commerce_app.databinding.ActivityEditProfilesBinding
import com.jubayedalam.e_commerce_app.network.checkout.addressGet.AddressRetrofitInstance
import com.jubayedalam.e_commerce_app.network.profile.editProfile.EditProfileRetrofitInstance
import com.jubayedalam.e_commerce_app.repository.checkout.addressGet.AddressRepository
import com.jubayedalam.e_commerce_app.repository.profile.editProfile.EditProfileRepository
import com.jubayedalam.e_commerce_app.ui.activity.checkout.activity.CheckoutNewAddressActivity
import com.jubayedalam.e_commerce_app.ui.adapter.checkout.addressGet.AddressAdapter
import com.jubayedalam.e_commerce_app.utils.sharedPreferences.SharedPreferencesManager
import com.jubayedalam.e_commerce_app.utils.stateManagement.Response
import com.jubayedalam.e_commerce_app.utils.stateManagement.Response2
import com.jubayedalam.e_commerce_app.viewModel.checkout.addressGet.AddressViewModel
import com.jubayedalam.e_commerce_app.viewModel.checkout.addressGet.AddressViewModelFactory
import com.jubayedalam.e_commerce_app.viewModel.profile.editProfile.EditProfileViewModel
import com.jubayedalam.e_commerce_app.viewModel.profile.editProfile.EditProfileViewModelFactory
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody

class EditProfilesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfilesBinding
    private lateinit var viewModel: EditProfileViewModel
    private lateinit var sharedPreferencesManager: SharedPreferencesManager

    private lateinit var addressViewModel: AddressViewModel
    private lateinit var addressAdapter: AddressAdapter

    private var imageUri: Uri? = null
    private var selectedAddressId: Int? = null
    private var getAddressId : Int? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfilesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        getAndSetTheData()

        sharedPreferencesManager = SharedPreferencesManager(this)

        binding.apply {
            backArrow.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
            tvNewAddress.setOnClickListener {
                startActivity(
                    Intent(
                        applicationContext,
                        CheckoutNewAddressActivity::class.java
                    )
                )
            }
        }

        // with library for image picker
        binding.imageUpload.setOnClickListener {
            ImagePicker.with(this)
                .crop()                    //Crop image(Optional), Check Customization for more option
                .compress(1024)            //Final image size will be less than 1 MB(Optional)
                .maxResultSize(
                    1080,
                    1080
                )
                .start()
        }

        binding.btnSave.setOnClickListener {

            if (sharedPreferencesManager.isLoggedIn()){
                updatedUserProfile()
            }else{
                Toast.makeText(this, "not login", Toast.LENGTH_SHORT).show()
            }

        }


    }



    private fun updatedUserProfile() {
        viewModel.editProfileResponseLiveData.observe(this) { response ->
            when (response) {
                is Response2.Success -> {
                    response.data?.let { handleSuccess(it) }
                }
                is Response2.Error -> {
                    handleError(response.errorMessage ?: "Unknown error")
                }
                is Response2.Loading -> {
                    Log.d("EditProfileActivity", "Loading...")
                }
            }
        }

        val editedUser = createEditProfileUserFromInputs()
        val token = "Token ${sharedPreferencesManager.getToken()}"
        val imagePart = imageUri?.let { uri ->
            createImagePart(uri)
        }

        editedUser.id?.let {
            editedUser.first_name?.let { it1 ->
                editedUser.last_name?.let { it2 ->
                    editedUser.email?.let { it3 ->
                        viewModel.updateUserInfo(
                            it,
                            it1,
                            it2,
                            it3,
                            editedUser.mobile,
                            editedUser.billing_info,

                            editedUser.hide_section,
                            imageUri,
                            token
                        )
                    }
                }
            }
        }
    }

    private fun createEditProfileUserFromInputs(): EditProfileUser {
        val firstName = binding.edFirstName.text.toString()
        val lastName = binding.edLastName.text.toString()
        val email = binding.edEmail.text.toString()
        val mobile = binding.edPhone.text.toString()
        val billingInfo =
            addressAdapter.getSelectedAddress()?.id // Assuming you have an EditText for billing info
        val billingInfoDetails = "" // Assuming you have an EditText for billing info details
        val hideSection = false // Assuming you have a CheckBox for hiding section

        return EditProfileUser(
            id = 1, // You may not need to specify ID for editing profile
            first_name = firstName,
            last_name = lastName,
            email = email,
            mobile = mobile,
            image = imageUri.toString(),
            image_url = "",
            billing_info = billingInfo,
//            billing_info_details = billingInfoDetails,
            hide_section = hideSection
        )
    }

    private fun createImagePart(imageUri: Uri?): MultipartBody.Part? {
        return imageUri?.let { uri ->
            val inputStream = contentResolver.openInputStream(uri)
            val imageByteArray = inputStream?.readBytes()
            val imageRequestBody = imageByteArray?.let {
                RequestBody.create(MediaType.parse("image/*"), it)
            }
            imageRequestBody?.let {
                MultipartBody.Part.createFormData("image", "profile_image.jpg", it)
            }
        }
    }

    private fun handleSuccess(data: EditProfileResponse) {
        // Handle success response
        Toast.makeText(this, "Profile updated successfully", Toast.LENGTH_SHORT).show()
        Log.d("EditProfileActivity", "Profile updated: $data")
        finish()
    }

    private fun handleError(errorMessage: String) {
        // Handle error response
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        Log.e("EditProfileActivity", "Error: $errorMessage")
    }



    private fun initViewModel() {
        val repository = EditProfileRepository(EditProfileRetrofitInstance.apiService)
        val factory = EditProfileViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[EditProfileViewModel::class.java]

    }

    // with library for image picker
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            val uri: Uri = data?.data!!

            imageUri = uri

            // Use Uri object instead of File to avoid storage permissions
            binding.profileImage.setImageURI(uri)
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        }
    }

    private fun getAndSetTheData() {
        val firstName = intent.getStringExtra("first_name")
        val lastName = intent.getStringExtra("last_name")
        val email = intent.getStringExtra("email")
        val mobile = intent.getStringExtra("mobile")
        val image = intent.getStringExtra("image")
        getAddressId = intent.getIntExtra("address",0)


        Log.d("New AddressId", getAddressId.toString())


        binding.edFirstName.setText(firstName)
        binding.edLastName.setText(lastName)
        binding.edEmail.setText(email)
        binding.edPhone.setText(mobile)
        binding.edGender.setText("Male")
        binding.edEmail.isEnabled = false // Disable editing
        // Load product image using Glide
        Glide.with(this)
            .load(image)
            .placeholder(com.denzcoskun.imageslider.R.drawable.default_loading)
            .error(com.denzcoskun.imageslider.R.drawable.default_error)
            .into(binding.profileImage)

        initAddressSetup()

    }

    private fun initAddressSetup() {
        // Initialize ViewModel
        val repository = AddressRepository(AddressRetrofitInstance.addressApiService)
        val factory = AddressViewModelFactory(repository)
        addressViewModel = ViewModelProvider(this, factory)[AddressViewModel::class.java]

        // Initialize RecyclerView and adapter
        addressAdapter = AddressAdapter(emptyList())
        binding.rvAddress.apply {
            adapter = addressAdapter
            layoutManager = LinearLayoutManager(context)

        }

        // Observe addresses LiveData
        addressViewModel.addresses.observe(this, Observer { response ->
            when (response) {
                is Response.Loading -> {
                    // Show loading state if needed
                }
                is Response.Success -> {
                    response.data?.let { addresses ->
                        addressAdapter.updateData(addresses, getAddressId)
                        Toast.makeText(this, "data show success", Toast.LENGTH_SHORT).show()
                        Log.d("GetAddressId", getAddressId.toString())
                    }
                }
                is Response.Error -> {
                    // Handle error state if needed
                    Toast.makeText(this, "data not show", Toast.LENGTH_SHORT).show()
                }
            }
        })
        // Fetch addresses
        addressViewModel.getAllAddresses()
    }

}