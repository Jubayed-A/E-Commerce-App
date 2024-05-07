package com.jubayedalam.e_commerce_app.ui.activity.checkout.stepper

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.jubayedalam.e_commerce_app.data.checkout.addressGet.Address
import com.jubayedalam.e_commerce_app.databinding.ActivityCheckoutBinding
import com.jubayedalam.e_commerce_app.ui.activity.checkout.fragments.CheckoutConfirmationFragment
import com.jubayedalam.e_commerce_app.ui.activity.checkout.fragments.CheckoutInfoFragment
import com.jubayedalam.e_commerce_app.ui.activity.checkout.fragments.CheckoutPaymentFragment

class CheckoutActivity : AppCompatActivity() , ButtonClickListener {

    private lateinit var binding: ActivityCheckoutBinding
    private lateinit var fragments : List<Fragment>

    private lateinit var selectedAddress: Address



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Assuming you have an instance of Address
        selectedAddress = Address()

        fragments = listOf(
            CheckoutInfoFragment.newInstance(),
            CheckoutPaymentFragment.newInstance(selectedAddress),
            CheckoutConfirmationFragment.newInstance(null),
            // Add more fragments as needed
        )

        // Set up ViewPager adapter
        val adapter = ViewPagerAdapter(supportFragmentManager, fragments ,null)
        binding.viewPager.adapter = adapter
        // Connect StepperIndicator with ViewPager
        binding.stepperIndicator.setViewPager(binding.viewPager)

        // After setting up the adapter and ViewPager
        // Set the button click listener after setting up the ViewPager adapter
        val checkoutInfoFragment = fragments[0] as? CheckoutInfoFragment
        checkoutInfoFragment?.setButtonClickListener(this)
//        val checkoutPaymentFragment = fragments[1] as? CheckoutPaymentFragment
//        checkoutPaymentFragment?.setButtonClickListener(this)


    }

    override fun onButtonClicked(data: Any?) {
        // Handle button click event here
        // Set up ViewPager adapter
        val adapter = ViewPagerAdapter(supportFragmentManager, fragments, data)
        binding.viewPager.adapter = adapter
        // Connect StepperIndicator with ViewPager
        binding.stepperIndicator.setViewPager(binding.viewPager)

        Log.d("CheckoutActivity", data.toString())
        val currentItem = binding.viewPager.currentItem
        when (currentItem) {
            0 -> {
                // User clicked the button in CheckoutInfoFragment
                // Switch to the next fragment (CheckoutPaymentFragment)
                binding.viewPager.setCurrentItem(currentItem + 1, true)
            }
            1 -> {
                // User clicked the button in CheckoutPaymentFragment
                // Switch to the next fragment (CheckoutConfirmationFragment)
                binding.viewPager.setCurrentItem(currentItem + 1, true)
            }
            // Add more cases if needed
            else -> {
                // Do nothing or handle additional cases
            }
        }
    }


}