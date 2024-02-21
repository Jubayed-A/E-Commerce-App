package com.example.e_comstarterdevsstream.ui.activity.checkout.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.e_comstarterdevsstream.databinding.ActivityCheckoutPaymentAddBinding

class CheckoutPaymentAddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheckoutPaymentAddBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutPaymentAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}