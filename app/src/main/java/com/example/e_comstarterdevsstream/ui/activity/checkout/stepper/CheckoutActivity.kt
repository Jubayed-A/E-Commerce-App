package com.example.e_comstarterdevsstream.ui.activity.checkout.stepper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.e_comstarterdevsstream.databinding.ActivityCheckoutBinding
import com.example.e_comstarterdevsstream.ui.activity.checkout.fragments.CheckoutConfirmationFragment
import com.example.e_comstarterdevsstream.ui.activity.checkout.fragments.CheckoutInfoFragment
import com.example.e_comstarterdevsstream.ui.activity.checkout.fragments.CheckoutPaymentFragment

class CheckoutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheckoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragments = listOf(
            CheckoutInfoFragment.newInstance(),
            CheckoutPaymentFragment.newInstance(),
            CheckoutConfirmationFragment.newInstance(),
            // Add more fragments as needed
        )


        // Set up ViewPager adapter
        val adapter = ViewPagerAdapter(supportFragmentManager,fragments)
        binding.viewPager.adapter = adapter


        // Connect StepperIndicator with ViewPager
        binding.stepperIndicator.setViewPager(binding.viewPager)


    }
}