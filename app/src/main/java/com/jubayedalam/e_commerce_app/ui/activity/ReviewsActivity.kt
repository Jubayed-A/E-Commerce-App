package com.jubayedalam.e_commerce_app.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jubayedalam.e_commerce_app.databinding.ActivityReviewsBinding

class ReviewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReviewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReviewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBackArrow.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }


    }
}