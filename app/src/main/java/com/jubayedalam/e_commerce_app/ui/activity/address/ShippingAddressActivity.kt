package com.jubayedalam.e_commerce_app.ui.activity.address

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jubayedalam.e_commerce_app.databinding.ActivityShippingAddressBinding
import com.jubayedalam.e_commerce_app.network.checkout.addressGet.AddressRetrofitInstance
import com.jubayedalam.e_commerce_app.repository.checkout.addressGet.AddressRepository
import com.jubayedalam.e_commerce_app.ui.activity.checkout.activity.CheckoutNewAddressActivity
import com.jubayedalam.e_commerce_app.ui.adapter.checkout.addressGet.AddressAdapter
import com.jubayedalam.e_commerce_app.utils.stateManagement.Response
import com.jubayedalam.e_commerce_app.viewModel.checkout.addressGet.AddressViewModel
import com.jubayedalam.e_commerce_app.viewModel.checkout.addressGet.AddressViewModelFactory

class ShippingAddressActivity : AppCompatActivity() {

private lateinit var binding: ActivityShippingAddressBinding

    private lateinit var addressViewModel: AddressViewModel
    private lateinit var addressAdapter: AddressAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShippingAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize ViewModel
        val repository = AddressRepository(AddressRetrofitInstance.addressApiService)
        val factory = AddressViewModelFactory(repository)
        addressViewModel = ViewModelProvider(this, factory)[AddressViewModel::class.java]

        // Initialize RecyclerView and adapter
        addressAdapter = AddressAdapter(emptyList())
        binding.rvAddress.apply {
            adapter = addressAdapter
            layoutManager = LinearLayoutManager(this@ShippingAddressActivity)




        }

        binding.NewAddressAdd.setOnClickListener {
            startActivity(Intent(applicationContext, CheckoutNewAddressActivity::class.java))
        }

        binding.backArrow.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        // Observe addresses LiveData
        addressViewModel.addresses.observe(this, Observer { response ->
            when (response) {
                is Response.Loading -> {
                    // Show loading state if needed
                }
                is Response.Success -> {
                    response.data?.let { addresses ->
                        addressAdapter.updateData(addresses)
                        Toast.makeText(this, "data show success", Toast.LENGTH_SHORT).show()
                    }
                }
                is Response.Error -> {
                    // Handle error state if needed
                    Toast.makeText(this, "data not show", Toast.LENGTH_SHORT).show()
                }
            }
        })

        // Fetch addresses
        addressViewModel.getAllAddresses()
    }
}