package com.example.e_comstarterdevsstream.ui.activity.checkout.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.e_comstarterdevsstream.databinding.FragmentCheckoutPaymentsBinding
import com.example.e_comstarterdevsstream.ui.activity.checkout.activity.CheckoutPaymentAddActivity

class CheckoutPaymentFragment : Fragment() {

    private lateinit var binding: FragmentCheckoutPaymentsBinding

    companion object {
        // Factory method to create a new instance of the fragment for stepper
        fun newInstance(): CheckoutPaymentFragment {
            return CheckoutPaymentFragment()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCheckoutPaymentsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnContinue.setOnClickListener {
            startActivity(Intent(requireContext(), CheckoutConfirmationFragment::class.java))
        }

        binding.tvNewCards.setOnClickListener {
            startActivity(Intent(requireContext(), CheckoutPaymentAddActivity::class.java))
        }

    }

}