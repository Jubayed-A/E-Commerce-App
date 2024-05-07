package com.jubayedalam.e_commerce_app.ui.fragment.shopping.cart

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jubayedalam.e_commerce_app.databinding.FragmentCartBinding
import com.jubayedalam.e_commerce_app.room.CartDB
import com.jubayedalam.e_commerce_app.room.CartEntity
import com.jubayedalam.e_commerce_app.ui.activity.checkout.stepper.CheckoutActivity
import com.jubayedalam.e_commerce_app.ui.adapter.CartAdapter
import com.jubayedalam.e_commerce_app.utils.stateManagement.Response
import com.jubayedalam.e_commerce_app.viewModel.cart.CartViewModel

class CartFragment : Fragment(),CartAdapter.OnPlusClickListener, CartAdapter.OnMinusClickListener {

    lateinit var binding: FragmentCartBinding
    private lateinit var cartAdapter: CartAdapter
    private lateinit var viewModel: CartViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            btnBackArrow.setOnClickListener {
                requireActivity().onBackPressed()
            }

            btnCheckout.setOnClickListener {
                // Navigate to ProductDetailsActivity with the product ID
                val intent = Intent(requireContext(), CheckoutActivity::class.java)
                intent.apply {
                    /*putExtra("product_id", cartAdapter.product.id)
                    putExtra("product_name", product.name)
                    putExtra("product_price", "৳ ${product.price}")
                    putExtra("product_description", product.description)
                    putExtra("product_image_url", product.thumbUrl)*/
                }
                startActivity(intent)
            }

        }

        // Initialize ViewModel
        viewModel = ViewModelProvider(this)[CartViewModel::class.java]

        // Initialize RecyclerView and adapter
        binding.rvCart.apply {
            cartAdapter = CartAdapter(CartDB.getDatabase(requireContext()))
            adapter = cartAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        // Set delete click listener
        cartAdapter.setOnDeleteClickListener { product ->
            // Delete the product from the database
            viewModel.deleteProduct(product)
            Toast.makeText(requireContext(), "The item is deleted", Toast.LENGTH_SHORT).show()
        }


        // Observe the cart response from the ViewModel
        viewModel.cartResponse.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Response.Loading -> {
                    // Show loading state
                    showCartShimmer()
                }

                is Response.Success -> {
                    // Hide loading state and update adapter with data
                    hideCartShimmer()
                    response.data?.let { products ->
                        cartAdapter.submitList(products)
                        val subTotalPrice = calculateSubTotalPrice(products)
                        binding.tvSubTotalPrice.text = "৳ ${"%.2f".format(subTotalPrice)}"
                        val totalPrice = calculateTotalPrice(subTotalPrice)
                        binding.tvTotalTextPrice.text = "৳ ${"%.2f".format(totalPrice)}" // Update the totalPrice TextView
                        binding.tvShippingFeePrice.text = "৳ 0.0"
                        binding.tvEstimatingTaxPrice.text = "৳ 0.0"
                    }
                }

                is Response.Error -> {
                    // Show error state
                    hideCartShimmer()
                    Toast.makeText(requireContext(), response.errorMessage, Toast.LENGTH_SHORT)
                        .show()
                    Log.d("Error", response.errorMessage.toString())
                }
            }
        })

    }

    private fun showCartShimmer() {
        binding.shimmerLayoutCart.startShimmer()
        binding.shimmerLayoutCart.visibility = View.VISIBLE
        binding.rvCart.visibility = View.GONE
    }

    private fun hideCartShimmer() {
        binding.shimmerLayoutCart.stopShimmer()
        binding.shimmerLayoutCart.visibility = View.GONE
        binding.rvCart.visibility = View.VISIBLE
    }

    private fun calculateSubTotalPrice(products: List<CartEntity>): Double {
        var totalPrice = 0.0
        for (product in products) {
            totalPrice += product.productPrice.toDouble() * product.quantity
        }
        return totalPrice
    }

    private fun calculateTotalPrice(subTotalPrice: Double): Double {
        val shippingFee = 0.0 // Example shipping fee
        val taxPercentage = 0.0 // Example tax rate (10%)
        val taxAmount = subTotalPrice * taxPercentage
        return subTotalPrice + shippingFee + taxAmount
    }


    override fun onPlusClick(product: CartEntity) {
        // Increase the quantity
        product.quantity++
    }

    override fun onMinusClick(product: CartEntity) {
        // Decrease the quantity if greater than 0
        if (product.quantity > 0) {
            product.quantity--
            // Update the displayed quantity

            // Remove the item if quantity becomes 0
            if (product.quantity == 0) {
                viewModel.deleteProduct(product)
                cartAdapter.notifyDataSetChanged()
            }
        }
    }

}

