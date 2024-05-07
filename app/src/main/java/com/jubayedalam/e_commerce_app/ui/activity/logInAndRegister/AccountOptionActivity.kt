package com.jubayedalam.e_commerce_app.ui.activity.logInAndRegister

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jubayedalam.e_commerce_app.databinding.ActivityAccountOptionBinding

class AccountOptionActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAccountOptionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountOptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnRegister.setOnClickListener {
                startActivity(Intent(this@AccountOptionActivity, RegisterActivity::class.java))
            }
            btnLogIn.setOnClickListener {
                startActivity(Intent(this@AccountOptionActivity, LogInActivity::class.java))
            }
        }


    }

}