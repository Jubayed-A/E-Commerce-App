package com.jubayedalam.e_commerce_app.ui.activity.checkout.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jubayedalam.e_commerce_app.databinding.ActivityCheckoutPaymentAddBinding

class CheckoutPaymentAddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheckoutPaymentAddBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutPaymentAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBackArrow.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.btnSave.setOnClickListener {
            checkoutValidation()
        }


    }

    private fun checkoutValidation() {
        // Assume you have EditText fields for each input data
        val fullName = binding.edCardholderName.text.toString()
        val cardNumber = binding.edCardNumber.text.toString()
        val expDate = binding.edExpDate.text.toString()
        val cvv = binding.edCVV.text.toString()

        var isError = false // Flag to track if any error occurred

        if (fullName.isEmpty()) {
            binding.edCardholderName.error = "⚠ Card Holder Name is required"
            isError = true
        }

        if (cardNumber.isEmpty()) {
            binding.edCardNumber.error = "⚠ Card Number is required"
            isError = true
        }

        if (expDate.isEmpty()) {
            binding.edExpDate.error = "⚠ ExpDate is required"
            isError = true
        }

        if (cvv.isEmpty()) {
            binding.edCVV.error = "⚠ CVV is required"
            isError = true
        }

        // Check if all fields are filled and there are no errors
        if (!isError) {
            // Show success message
            Toast.makeText(this, "All fields are filled!", Toast.LENGTH_SHORT).show()
        } else {
            // Show message indicating which fields are not filled
            val message = "Please fill up the following fields: "
            val missingFields = mutableListOf<String>()
            if (fullName.isEmpty()) missingFields.add("Full Name")

            val missingFieldsString = missingFields.joinToString(", ")
            Toast.makeText(this, "$message $missingFieldsString", Toast.LENGTH_SHORT).show()
        }
    }


}