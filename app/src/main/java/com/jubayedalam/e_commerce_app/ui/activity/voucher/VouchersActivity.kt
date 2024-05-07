package com.jubayedalam.e_commerce_app.ui.activity.voucher

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jubayedalam.e_commerce_app.databinding.ActivityVouchersBinding

class VouchersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVouchersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVouchersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.NewVoucherAdd.setOnClickListener {
            startActivity(Intent(applicationContext, VoucherAddActivity::class.java))
        }

        binding.backArrow.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }


    }
}