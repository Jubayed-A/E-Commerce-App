package com.jubayedalam.e_commerce_app.ui.activity.favourit

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.jubayedalam.e_commerce_app.databinding.ActivityMyFavouritesBinding
import com.jubayedalam.e_commerce_app.utils.stateManagement.Response
import com.jubayedalam.e_commerce_app.viewModel.product.fav.FavProductViewModel

class MyFavouritesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyFavouritesBinding
    private lateinit var favProductViewModel: FavProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyFavouritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBackArrow.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }




    }

    // Inside onCreate of FavoriteProductsActivity
    private fun fetchFavoriteProducts() {
        // Initialize the CartViewModel
        favProductViewModel = ViewModelProvider(this)[FavProductViewModel::class.java]

        // Observe the list of favorite products
        favProductViewModel.favProductResponse.observe(this) { response ->
            when (response) {
                is Response.Success -> {
                    val favoriteProducts = response.data ?: emptyList()
                    // Update UI with the list of favorite products
                    // For example, update RecyclerView adapter with favoriteProducts
                }

                is Response.Error -> {
                    Toast.makeText(this, "Failed to fetch favorite products", Toast.LENGTH_SHORT)
                        .show()
                }

                is Response.Loading -> {
                    // Show loading indicator if needed
                }
            }
        }

        // Fetch favorite products
        favProductViewModel.getAllProducts()
    }


}