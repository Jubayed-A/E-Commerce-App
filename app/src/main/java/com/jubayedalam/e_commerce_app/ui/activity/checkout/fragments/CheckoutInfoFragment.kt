package com.jubayedalam.e_commerce_app.ui.activity.checkout.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jubayedalam.e_commerce_app.databinding.FragmentCheckoutInfoBinding
import com.jubayedalam.e_commerce_app.network.checkout.addressGet.AddressRetrofitInstance
import com.jubayedalam.e_commerce_app.repository.checkout.addressGet.AddressRepository
import com.jubayedalam.e_commerce_app.ui.activity.checkout.stepper.ButtonClickListener
import com.jubayedalam.e_commerce_app.ui.adapter.checkout.addressGet.AddressAdapter
import com.jubayedalam.e_commerce_app.utils.stateManagement.Response
import com.jubayedalam.e_commerce_app.viewModel.checkout.addressGet.AddressViewModel
import com.jubayedalam.e_commerce_app.viewModel.checkout.addressGet.AddressViewModelFactory

class CheckoutInfoFragment : Fragment() {

    private lateinit var binding: FragmentCheckoutInfoBinding
    private var buttonClickListener: ButtonClickListener? = null

    private lateinit var addressViewModel: AddressViewModel
    private lateinit var addressAdapter: AddressAdapter

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

        binding.apply {
            btnBackArrow.setOnClickListener {
                requireActivity().finish()
            }
            tvNewAddress.setOnClickListener {
                binding.checkoutInfoSection.visibility = View.GONE
                binding.checkoutNewAddress.visibility = View.VISIBLE
            }
        }

        binding.btnSave.setOnClickListener {
            val validationSuccessful = checkoutValidation()

            if (validationSuccessful) {
                // Validation successful, show checkout info section and hide new address section
                binding.checkoutInfoSection.visibility = View.VISIBLE
                binding.checkoutNewAddress.visibility = View.GONE
            } else {
                // Validation not completed, show message or handle accordingly
                Toast.makeText(requireContext(), "Validation not completed. Please fill in all required fields.", Toast.LENGTH_SHORT).show()
            }
        }

/*        binding.btnContinue.setOnClickListener {
            // Trigger the interface method when the button is clicked
            buttonClickListener?.onButtonClicked()
        }*/


        initAddressSetup()

        binding.btnContinue.setOnClickListener {
            val selectedAddress = addressAdapter.getSelectedAddress()

            if (selectedAddress != null) {
                // Address is selected, proceed to the next page
                buttonClickListener?.onButtonClicked(selectedAddress)
                Toast.makeText(requireContext(), "Going to payment section", Toast.LENGTH_SHORT).show()
            } else {
                // No address selected, show an error message
                Toast.makeText(context, "Please select an address", Toast.LENGTH_SHORT).show()
            }
        }


    }

    private fun initAddressSetup() {
        // Initialize ViewModel
        val repository = AddressRepository(AddressRetrofitInstance.addressApiService)
        val factory = AddressViewModelFactory(repository)
        addressViewModel = ViewModelProvider(this, factory)[AddressViewModel::class.java]

        // Initialize RecyclerView and adapter
        addressAdapter = AddressAdapter(emptyList())
        binding.rvAddress.apply {
            adapter = addressAdapter
            layoutManager = LinearLayoutManager(requireContext())

        }

        // Observe addresses LiveData
        addressViewModel.addresses.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Response.Loading -> {
                    // Show loading state if needed
                }
                is Response.Success -> {
                    response.data?.let { addresses ->
                        addressAdapter.updateData(addresses, getAddressId)
                        Toast.makeText(requireContext(), "data show success", Toast.LENGTH_SHORT).show()
                    }
                }
                is Response.Error -> {
                    // Handle error state if needed
                    Toast.makeText(requireContext(), "data not show", Toast.LENGTH_SHORT).show()
                }
            }
        })

        // Fetch addresses
        addressViewModel.getAllAddresses()

    }

    // Setter method for the listener
    fun setButtonClickListener(listener: ButtonClickListener) {
        buttonClickListener = listener
    }


    // for new address validation
    private fun checkoutValidation(): Boolean {
        // Assume you have EditText fields for each input data
        val fullName = binding.edFullName.text.toString()
        val address = binding.edAddress.text.toString()
        val zip = binding.edZip.text.toString()
        val city = binding.edCity.text.toString()
        val phone = binding.edPhone.text.toString()

        var isError = false // Flag to track if any error occurred

        if (fullName.isEmpty()) {
            binding.edFullName.error = "⚠ Full Name is required"
            isError = true
        }

        if (address.isEmpty()) {
            binding.edAddress.error = "⚠ Address is required"
            isError = true
        }

        if (zip.isEmpty()) {
            binding.edZip.error = "⚠ Zip is required"
            isError = true
        }

        if (city.isEmpty()) {
            binding.edCity.error = "⚠ City/District Number is required"
            isError = true
        }

        if (phone.isEmpty()) {
            binding.edPhone.error = "⚠ Phone number is required"
            isError = true
        }

        // Return false if there are errors
        return !isError

    }

}