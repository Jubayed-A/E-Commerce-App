package com.example.e_comstarterdevsstream.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.e_comstarterdevsstream.ui.fragment.shopping.CartFragment
import com.example.e_comstarterdevsstream.databinding.ActivityProductDetailsBinding

class ProductDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.apply {


            btnAddToCart.setOnClickListener{
                startActivity(Intent(this@ProductDetailsActivity, CartFragment::class.java))
            }


        }


    }

}