package com.example.e_comstarterdevsstream.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.e_comstarterdevsstream.databinding.ActivitySplashBinding

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

}