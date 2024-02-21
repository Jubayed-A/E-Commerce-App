package com.example.e_comstarterdevsstream.ui.activity.checkout.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.e_comstarterdevsstream.databinding.FragmentCheckoutInfoBinding
import com.example.e_comstarterdevsstream.ui.activity.checkout.stepper.ButtonClickListener

class CheckoutInfoFragment : Fragment() {

    private lateinit var binding: FragmentCheckoutInfoBinding
    private var buttonClickListener: ButtonClickListener? = null

    companion object {
        // Factory method to create a new instance of the fragment for stepper
        fun newInstance(): CheckoutInfoFragment {
            return CheckoutInfoFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCheckoutInfoBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvNewAddress.setOnClickListener {
            binding.checkoutInfoSection.visibility = View.GONE
            binding.checkoutNewAddress.visibility = View.VISIBLE
        }
        binding.btnSave.setOnClickListener {
            binding.checkoutInfoSection.visibility = View.VISIBLE
            binding.checkoutNewAddress.visibility = View.GONE
        }

        binding.btnContinue.setOnClickListener {
            // Trigger the interface method when the button is clicked
            buttonClickListener?.onButtonClicked()
        }


    }

    // Setter method for the listener
    fun setButtonClickListener(listener: ButtonClickListener) {
        buttonClickListener = listener
    }


}