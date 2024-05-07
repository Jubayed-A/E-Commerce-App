package com.jubayedalam.e_commerce_app.ui.activity.checkout.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jubayedalam.e_commerce_app.data.checkout.address.NewAddress
import com.jubayedalam.e_commerce_app.databinding.ActivityCheckoutNewAddressBinding
import com.jubayedalam.e_commerce_app.network.checkout.address.NewAddressRetrofitInstance
import com.jubayedalam.e_commerce_app.repository.checkout.address.NewAddressRepository
import com.jubayedalam.e_commerce_app.ui.activity.address.ShippingAddressActivity
import com.jubayedalam.e_commerce_app.utils.stateManagement.Response
import com.jubayedalam.e_commerce_app.viewModel.checkout.address.NewAddressViewModel
import com.jubayedalam.e_commerce_app.viewModel.checkout.address.NewAddressViewModelFactory

class CheckoutNewAddressActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheckoutNewAddressBinding

    private lateinit var newAddressViewModel: NewAddressViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutNewAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backArrow.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Initialize the ViewModel
        val repository = NewAddressRepository(NewAddressRetrofitInstance.newAddressApiService)
        val factory = NewAddressViewModelFactory(repository)
        newAddressViewModel = ViewModelProvider(
            this,factory
        )[NewAddressViewModel::class.java]


        binding.btnSave.setOnClickListener {
            initialNewAddress()
        }

    }

    private fun initialNewAddress() {
        // Collect user input from EditText fields
        val newAddress = NewAddress(
            idn = 1,
            first_name = binding.edFullName.text.toString(),
            mobile = binding.edPhone.text.toString(),
            town_city = binding.edCity.text.toString(),
            postcode_zip = binding.edZip.text.toString(),
            street_address = binding.edAddress.text.toString(),
            last_name = "",
            state_county = "",
            company_name = "",
            country = "Bangladesh",
            division = "",
            district = "",
            email = "user@example.com"

        )

        // Pass the newAddress object to the ViewModel to save it
        newAddressViewModel.setNewAddress(newAddress)


        // Observe the response LiveData
        newAddressViewModel.response.observe(this, Observer { response ->
            when (response) {
                is Response.Success -> {
                    // Show a success message or update UI accordingly
                    val data = response.data
                    val intent = Intent(this, ShippingAddressActivity::class.java)
                    startActivity(intent)
                    finish() // Optionally finish this activity to prevent going back to it when pressing back
                }

                is Response.Error -> {
                    Log.d("Error", response.errorMessage.toString())
                    Toast.makeText(this, "Error: ${response.errorMessage}", Toast.LENGTH_SHORT).show()
                }

                else -> {
                    Unit
                }
            }
        })

    }


    private fun checkoutValidation() {
        // Assume you have EditText fields for each input data
        val fullName = binding.edFullName.text.toString()
        val address = binding.edAddress.text.toString()
        val zip = binding.edZip.text.toString()
        val city = binding.edCity.text.toString()
        val phone = binding.edPhone.text.toString()

        if (fullName.isEmpty()) {
            binding.edFullName.error = "⚠ Full Name is required"
        }

        if (address.isEmpty()) {
            binding.edAddress.error = "⚠ Address is required"
        }

        if (zip.isEmpty()) {
            binding.edZip.error = "⚠ Zip is required"
        }

        if (city.isEmpty()) {
            binding.edCity.error = "⚠ City/District Number is required"
        }

        if (phone.isEmpty()) {
            binding.edPhone.error = "⚠ Phone number is required"
        }

        // Check if all fields are filled
        if (fullName.isNotEmpty() && address.isNotEmpty() && zip.isNotEmpty() && city.isNotEmpty() && phone.isNotEmpty()) {
            // Show success message
            Toast.makeText(this, "All fields are filled!", Toast.LENGTH_SHORT).show()
        } else {
            // Show message indicating which fields are not filled
            val message = "Please fill up the following fields: "
            val missingFields = mutableListOf<String>()
            if (fullName.isEmpty()) missingFields.add("Full Name")
            if (address.isEmpty()) missingFields.add("Address")
            if (zip.isEmpty()) missingFields.add("Zip")
            if (city.isEmpty()) missingFields.add("City/District")
            if (phone.isEmpty()) missingFields.add("Phone Number")

            val missingFieldsString = missingFields.joinToString(", ")
            Toast.makeText(this, "$message $missingFieldsString", Toast.LENGTH_SHORT).show()
        }

    }


}