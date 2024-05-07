package com.jubayedalam.e_commerce_app.ui.activity.checkout.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jubayedalam.e_commerce_app.databinding.FragmentCheckoutConfirmationBinding
import com.jubayedalam.e_commerce_app.ui.activity.checkout.activity.CheckoutThankYouActivity

class CheckoutConfirmationFragment : Fragment() {

    private lateinit var binding: FragmentCheckoutConfirmationBinding

    companion object {
        // Factory method to create a new instance of the fragment for stepper
        fun newInstance(data: Any?): CheckoutConfirmationFragment {
            return CheckoutConfirmationFragment()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCheckoutConfirmationBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBackArrow.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.btnPlaceOrder.setOnClickListener {
            startActivity(Intent(requireContext(), CheckoutThankYouActivity::class.java))
        }
    }

}