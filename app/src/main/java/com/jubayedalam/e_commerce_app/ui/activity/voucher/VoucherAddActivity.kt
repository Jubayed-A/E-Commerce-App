package com.jubayedalam.e_commerce_app.ui.activity.voucher

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jubayedalam.e_commerce_app.databinding.ActivityVoucherAddBinding

class VoucherAddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVoucherAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVoucherAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backArrow.setOnClickListener {
            finish()
        }

        binding.btnSave.setOnClickListener {
            checkoutValidation()
        }

    }

    private fun checkoutValidation() {
        // Assume you have EditText fields for each input data
        val voucherName = binding.edVoucherName.text.toString()
        val voucherDiscount = binding.edVoucherDiscount.text.toString()
        val expDate = binding.edExpDate.text.toString()
        val voucher = binding.edVoucher.text.toString()

        var isError = false // Flag to track if any error occurred

        if (voucherName.isEmpty()) {
            binding.edVoucherName.error = "⚠ Voucher Name is required"
            isError = true
        }

        if (voucherDiscount.isEmpty()) {
            binding.edVoucherDiscount.error = "⚠ Voucher Discount is required"
            isError = true
        }

        if (expDate.isEmpty()) {
            binding.edExpDate.error = "⚠ ExpDate is required"
            isError = true
        }

        if (voucher.isEmpty()) {
            binding.edVoucher.error = "⚠ Voucher is required"
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
            if (voucherName.isEmpty()) missingFields.add("Full Name")

            val missingFieldsString = missingFields.joinToString(", ")
            Toast.makeText(this, "$message $missingFieldsString", Toast.LENGTH_SHORT).show()
        }
    }

}