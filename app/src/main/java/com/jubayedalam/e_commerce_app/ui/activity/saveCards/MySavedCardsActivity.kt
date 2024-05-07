package com.jubayedalam.e_commerce_app.ui.activity.saveCards

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jubayedalam.e_commerce_app.databinding.ActivityMySavedCardsBinding
import com.jubayedalam.e_commerce_app.ui.activity.checkout.activity.CheckoutPaymentAddActivity

class MySavedCardsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMySavedCardsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMySavedCardsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.NewCardAdd.setOnClickListener {
            startActivity(Intent(applicationContext, CheckoutPaymentAddActivity::class.java))
        }

        binding.btnBackArrow.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }


    }
}