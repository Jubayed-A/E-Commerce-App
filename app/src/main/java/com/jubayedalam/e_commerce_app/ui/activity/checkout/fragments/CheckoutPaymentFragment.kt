package com.jubayedalam.e_commerce_app.ui.activity.checkout.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.jubayedalam.e_commerce_app.R
import com.jubayedalam.e_commerce_app.data.checkout.addressGet.Address
import com.jubayedalam.e_commerce_app.databinding.FragmentCheckoutPaymentsBinding
import com.jubayedalam.e_commerce_app.ui.activity.checkout.stepper.ButtonClickListener

class CheckoutPaymentFragment : Fragment(){

    private lateinit var binding: FragmentCheckoutPaymentsBinding
    private var buttonClickListener: ButtonClickListener? = null

    private lateinit var selectedAddress: Address


    private var isCashSelected = false
    private var isCardSelected = false
    private var isNetSelected = false


    private lateinit var getAddress: Any


    /*    companion object {
            // Factory method to create a new instance of the fragment for stepper
    //        fun newInstance(data: Any?): CheckoutPaymentFragment {
    //            return CheckoutPaymentFragment()
    //        }

            fun newInstance(data: Any?) = CheckoutPaymentFragment().apply {
                arguments = bundleOf(
                    "ARG_TEXT_TO_SHOW" to data
                )
            }

        }*/

    companion object {
        // Factory method to create a new instance of the fragment for stepper
        fun newInstance(selectedAddress: Address): CheckoutPaymentFragment {
            return CheckoutPaymentFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("ADDRESS_DATA", selectedAddress)
                }
            }
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

        /*getAddress = arguments?.getString("ARG_TEXT_TO_SHOW","")!!

        Log.d("after arguments", getAddress.toString())*/

        // Retrieve the data from arguments
        /*getAddress = arguments?.getString("ARG_TEXT_TO_SHOW") ?: ""

        Log.d("Received data", getAddress.toString())*/
//
//        val selectedAddress = arguments?.getParcelable<Address>("SELECTED_ADDRESS")
//        Log.d("RecivedAddress", selectedAddress.toString())

        //        Log.d("Received data", getAddress.toString())
//        Log.d("RecivedAddress", selectedAddress.toString())


        binding.apply {
            btnBackArrow.setOnClickListener {
                requireActivity().finish()
            }
            tvNewCards.setOnClickListener {
                binding.checkoutPaymentSection.visibility = View.GONE
                binding.checkoutPaymentNewCardSection.visibility = View.VISIBLE
            }
            btnSave.setOnClickListener {
                val validationSuccessful = checkoutValidation()

                if (validationSuccessful) {
                    // Validation successful, show checkout info section and hide new address section
                    binding.checkoutPaymentSection.visibility = View.VISIBLE
                    binding.checkoutPaymentNewCardSection.visibility = View.GONE
                } else {
                    // Validation not completed, show message or handle accordingly
                    Toast.makeText(
                        requireContext(),
                        "Validation not completed. Please fill in all required fields.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }


        // chose payment method code
        binding.apply {
            cashPaymentButton.setOnClickListener {
                choosePaymentMethod(it)
            }
            cardPaymentButton.setOnClickListener {
                choosePaymentMethod(it)
            }
            netPaymentButton.setOnClickListener {
                choosePaymentMethod(it)
            }
        }


        // Retrieve the data passed from the activity through arguments
        /*val addressData = arguments?.getParcelable<Address>("ADDRESS_DATA")
        if (addressData != null) {
            // Do something with the address data
            Log.d("CheckoutPaymentFragment", "Received address data: $addressData")
        } else {
            Log.e("CheckoutPaymentFragment", "No address data received")
        }*/

        selectedAddress = arguments?.getParcelable("ADDRESS_DATA") ?: Address()

        binding.tvPaymentMethodText.text = selectedAddress.first_name
        Log.d("AddressInPayment", selectedAddress.toString())

        binding.btnContinue.setOnClickListener {
            // Trigger the interface method when the button is clicked
            buttonClickListener?.onButtonClicked(selectedAddress)
        }

    }

    // Setter method for the listener
    fun setButtonClickListener(listener: ButtonClickListener) {
        buttonClickListener = listener
    }

    private fun checkoutValidation(): Boolean {
        // Assume you have EditText fields for each input data
        val fullName = binding.edCardholderName.text.toString()
        val cardNumber = binding.edCardNumber.text.toString()
        val expDate = binding.edExpDate.text.toString()
        val cvv = binding.edCVV.text.toString()

        var isError = false // Flag to track if any error occurred

        if (fullName.isEmpty()) {
            binding.edCardholderName.error = "⚠ Card Holder Name is required"
            isError = true
        }

        if (cardNumber.isEmpty()) {
            binding.edCardNumber.error = "⚠ Card Number is required"
            isError = true
        }

        if (expDate.isEmpty()) {
            binding.edExpDate.error = "⚠ ExpDate is required"
            isError = true
        }

        if (cvv.isEmpty()) {
            binding.edCVV.error = "⚠ CVV is required"
            isError = true
        }


        // Return false if there are errors
        return !isError

    }

    private fun choosePaymentMethod(view: View) {
        when (view.id) {
            R.id.cashPaymentButton -> {
                if (!isCashSelected) {
                    // Change background and tint color for cash payment method
                    binding.cashPaymentButton.setImageResource(R.drawable.ic_cash_night)
                    // Update selected state
                    isCashSelected = true
                    // Reset background and tint color for other payment methods
                    binding.cardPaymentButton.setImageResource(R.drawable.ic_card)
                    binding.netPaymentButton.setImageResource(R.drawable.ic_net)
                    // Update selected states for other payment methods
                    isCardSelected = false
                    isNetSelected = false
                }
            }

            R.id.cardPaymentButton -> {
                if (!isCardSelected) {
                    // Change background and tint color for card payment method
                    binding.cardPaymentButton.setImageResource(R.drawable.ic_card_night)
                    // Update selected state
                    isCardSelected = true
                    // Reset background and tint color for other payment methods
                    binding.cashPaymentButton.setImageResource(R.drawable.ic_cash)
                    binding.netPaymentButton.setImageResource(R.drawable.ic_net)
                    // Update selected states for other payment methods
                    isCashSelected = false
                    isNetSelected = false
                }
            }

            R.id.netPaymentButton -> {
                if (!isNetSelected) {
                    // Change background and tint color for net payment method
                    binding.netPaymentButton.setImageResource(R.drawable.ic_net_night)
                    // Update selected state
                    isNetSelected = true
                    // Reset background and tint color for other payment methods
                    binding.cashPaymentButton.setImageResource(R.drawable.ic_cash)
                    binding.cardPaymentButton.setImageResource(R.drawable.ic_card)
                    // Update selected states for other payment methods
                    isCashSelected = false
                    isCardSelected = false
                }
            }
        }
    }


}