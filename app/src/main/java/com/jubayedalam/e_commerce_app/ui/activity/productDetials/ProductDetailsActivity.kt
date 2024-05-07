package com.jubayedalam.e_commerce_app.ui.activity.productDetials

import android.os.Bundle
import android.text.Html
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.jubayedalam.e_commerce_app.data.home.trending.Product
import com.jubayedalam.e_commerce_app.databinding.ActivityProductDetailsBinding
import com.jubayedalam.e_commerce_app.room.CartEntity
import com.jubayedalam.e_commerce_app.room.fav.FavEntity
import com.jubayedalam.e_commerce_app.viewModel.cart.CartViewModel
import com.jubayedalam.e_commerce_app.viewModel.product.fav.FavProductViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailsBinding

    private lateinit var cartViewModel: CartViewModel
    private lateinit var favProductViewModel: FavProductViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val productId = intent.getIntExtra("product_id", -1)
        val productName = intent.getStringExtra("product_name")
        val productPrice = intent.getStringExtra("product_price")
        val productDescription = intent.getStringExtra("product_description")
        val productImageUrl = intent.getStringExtra("product_image_url")

        cartViewModel = ViewModelProvider(this)[CartViewModel::class.java]
        favProductViewModel = ViewModelProvider(this)[FavProductViewModel::class.java]


        getDataAndShowProductDetails()


        binding.apply {
            imageBack.setOnClickListener {
                onBackPressedDispatcher
            }

            binding.favIcon.setOnClickListener {
                if (productName != null && productPrice != null && productDescription != null && productImageUrl != null) {
                    // Initialize the product variable with the selected product data
                    val product = Product(
                        productId,
                        productName,
                        "",
                        "",
                        "",
                        "",
                        productImageUrl,
                        "",
                        productPrice
                    )
                    // Call the addToFavorites function with the initialized product variable
                    addToFavorites(product)
                } else {
                    Toast.makeText(
                        this@ProductDetailsActivity,
                        "Product data is incomplete",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }


            btnAddToCart.setOnClickListener {
                if (productName != null && productPrice != null && productDescription != null && productImageUrl != null) {
                    // Initialize the product variable with the selected product data
                    val product =
                        Product(
                            productId,
                            productName,
                            "",
                            "",
                            "",
                            "",
                            productImageUrl,
                            "",
                            productPrice
                        )
                    // Call the addToCart function with the initialized product variable
                    addToCart(product)
                } else {
                    Toast.makeText(
                        this@ProductDetailsActivity,
                        "Product data is incomplete",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }


    }


    private fun addToCart(product: Product) {
        val existingProduct = findProductInCart(product)
        if (existingProduct != null) {
            // Product already exists in the cart, increment its quantity
            existingProduct.quantity++
            // Update the cart in the ViewModel
            cartViewModel.updateProduct(existingProduct)
            Toast.makeText(this, "Product quantity increased", Toast.LENGTH_SHORT).show()
        } else {
            // Product doesn't exist in the cart, add it to the cart
            val cartEntity = CartEntity(
                0, product.id.toString(), product.name, product.price,
                product.thumbUrl, 1
            )
            // Insert the product into the Room database using the DAO
            insertProductIntoDatabase(cartEntity)
            Toast.makeText(this, "Product added to cart", Toast.LENGTH_SHORT).show()
        }
    }

    private fun findProductInCart(product: Product): CartEntity? {
        // Get the current list of products in the cart from the ViewModel
        val cartProducts = cartViewModel.cartResponse.value?.data ?: return null
        // Check if the selected product already exists in the cart
        for (item in cartProducts) {
            if (item.productId == product.id.toString()) {
                return item
            }
        }
        return null
    }


    // trending shop button click
    private fun insertProductIntoDatabase(productEntity: CartEntity) {
        // Execute the insertion operation in a background thread
        CoroutineScope(Dispatchers.IO).launch {
            cartViewModel.insertProduct(productEntity)
        }
    }


    // for show product details
    private fun getDataAndShowProductDetails() {

        // Retrieve product details from intent extras
        val productId = intent.getIntExtra("product_id", -1)
        val productName = intent.getStringExtra("product_name")
        val productPrice = intent.getStringExtra("product_price")
        val productDescription = intent.getStringExtra("product_description")
        val productImageUrl = intent.getStringExtra("product_image_url")

        val formattedDescription = formatHtmlText(productDescription)

        // Set product details to views
        binding.tvProductName.text = productName
        binding.tvProductPrice.text = productPrice
        binding.tvProductDescription.text = formattedDescription

        // Load product image using Glide
        Glide.with(this)
            .load(productImageUrl)
            .placeholder(com.denzcoskun.imageslider.R.drawable.default_loading)
            .error(com.denzcoskun.imageslider.R.drawable.default_error)
            .into(binding.viewPagerProductImages)

    }

    private fun formatHtmlText(htmlText: String?): String {
        return if (!htmlText.isNullOrEmpty()) {
            // Convert HTML-formatted text to plain text
            Html.fromHtml(htmlText).toString()
        } else {
            ""
        }
    }



    private fun addToFavorites(product: Product) {
        favProductViewModel.insertProduct(product.toFavProductEntity()) // Convert Product to CartEntity and insert into Room
        Toast.makeText(this, "Product added to favorites", Toast.LENGTH_SHORT).show()
    }

    // Extension function to convert Product to CartEntity
    private fun Product.toFavProductEntity(): FavEntity {
        return FavEntity(
            id = 0, // Auto-generated ID
            productId = id.toString(),
            productName = name,
            productPrice = price,
            imageUrl = thumbUrl
        )
    }


}