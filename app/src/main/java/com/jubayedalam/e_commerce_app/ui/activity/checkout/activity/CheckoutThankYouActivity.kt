package com.jubayedalam.e_commerce_app.ui.activity.checkout.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jubayedalam.e_commerce_app.databinding.ActivityCheckoutThankyouBinding
import com.jubayedalam.e_commerce_app.ui.activity.MainActivity

class CheckoutThankYouActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheckoutThankyouBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutThankyouBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnCloseIcon.setOnClickListener {
                startActivity(Intent(applicationContext, MainActivity::class.java))
            }
            btnContinueShopping.setOnClickListener {
                startActivity(Intent(applicationContext, MainActivity::class.java))
            }
        }




    }
}