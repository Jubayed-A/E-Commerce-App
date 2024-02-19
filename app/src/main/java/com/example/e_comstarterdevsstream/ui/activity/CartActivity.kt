package com.devsstore.devsstore.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.e_comstarterdevsstream.R
import com.example.e_comstarterdevsstream.databinding.ActivityCartBinding


class CartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
    }

}
