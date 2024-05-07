package com.jubayedalam.e_commerce_app.ui.activity.orders

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jubayedalam.e_commerce_app.databinding.ActivityOrderHistoryBinding

class OrderHistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrderHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBackArrow.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }



    }
}