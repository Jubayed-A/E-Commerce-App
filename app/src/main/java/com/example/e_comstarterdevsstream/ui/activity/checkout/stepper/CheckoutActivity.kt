package com.devsstore.devsstore.ui.activity.checkout.stepper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.devsstore.devsstore.databinding.ActivityCheckoutBinding
import com.devsstore.devsstore.ui.activity.checkout.fragments.CheckoutConfirmationFragment
import com.devsstore.devsstore.ui.activity.checkout.fragments.CheckoutInfoFragment
import com.devsstore.devsstore.ui.activity.checkout.fragments.CheckoutPaymentFragment

class CheckoutActivity : AppCompatActivity() , ButtonClickListener {

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
        val adapter = ViewPagerAdapter(supportFragmentManager, fragments)
        binding.viewPager.adapter = adapter


        // Connect StepperIndicator with ViewPager
        binding.stepperIndicator.setViewPager(binding.viewPager)

        // After setting up the adapter and ViewPager
        // Set the button click listener after setting up the ViewPager adapter
        val checkoutInfoFragment = fragments[0] as? CheckoutInfoFragment
        checkoutInfoFragment?.setButtonClickListener(this)
        val checkoutPaymentFragment = fragments[1] as? CheckoutPaymentFragment
        checkoutPaymentFragment?.setButtonClickListener(this)



    }


    override fun onButtonClicked() {
        // Handle button click event here
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
















    // like fragment

  /*  override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ActivityCheckoutBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragments = listOf(
            CheckoutInfoFragment.newInstance(),
            CheckoutPaymentFragment.newInstance(),
            CheckoutConfirmationFragment.newInstance(),
            // Add more fragments as needed
        )


        // Set up ViewPager adapter
        val adapter = ViewPagerAdapter(childFragmentManager,fragments)
        binding.viewPager.adapter = adapter


        // Connect StepperIndicator with ViewPager
        binding.stepperIndicator.setViewPager(binding.viewPager)



    }
*/






}