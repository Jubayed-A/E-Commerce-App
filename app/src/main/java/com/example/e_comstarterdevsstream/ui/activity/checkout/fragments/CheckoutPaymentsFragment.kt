package com.example.e_comstarterdevsstream.ui.activity.checkout.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.e_comstarterdevsstream.databinding.FragmentCheckoutPaymentsBinding
import com.example.e_comstarterdevsstream.ui.activity.checkout.stepper.ButtonClickListener

class CheckoutPaymentFragment : Fragment() {

    private lateinit var binding: FragmentCheckoutPaymentsBinding
    private var buttonClickListener: ButtonClickListener? = null

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
            // Trigger the interface method when the button is clicked
            buttonClickListener?.onButtonClicked()
        }

        binding.tvNewCards.setOnClickListener {
            binding.checkoutPaymentSection.visibility = View.GONE
            binding.checkoutPaymentNewCardSection.visibility = View.VISIBLE
        }
        binding.btnSave.setOnClickListener {
            binding.checkoutPaymentSection.visibility = View.VISIBLE
            binding.checkoutPaymentNewCardSection.visibility = View.GONE
        }

    }

    // Setter method for the listener
    fun setButtonClickListener(listener: ButtonClickListener) {
        buttonClickListener = listener
    }

}