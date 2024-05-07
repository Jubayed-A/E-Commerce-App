package com.jubayedalam.e_commerce_app.ui.fragment.shopping.product

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.jubayedalam.e_commerce_app.data.product.category.ProductCategoryList
import com.jubayedalam.e_commerce_app.data.product.item.ProductItem
import com.jubayedalam.e_commerce_app.databinding.FragmentSofaSetBinding
import com.jubayedalam.e_commerce_app.network.product.category.ProductCategoryRetrofitInstance
import com.jubayedalam.e_commerce_app.network.product.item.ProductItemRetrofitInstance
import com.jubayedalam.e_commerce_app.repository.product.category.ProductCategoryRepository
import com.jubayedalam.e_commerce_app.repository.product.item.ProductItemRepository
import com.jubayedalam.e_commerce_app.ui.activity.Filter.FilterActivity
import com.jubayedalam.e_commerce_app.ui.activity.productDetials.ProductDetailsActivity
import com.jubayedalam.e_commerce_app.ui.adapter.product.category.ProductCategoryAdapter
import com.jubayedalam.e_commerce_app.ui.adapter.product.item.ProductItemAdapter
import com.jubayedalam.e_commerce_app.utils.stateManagement.Response
import com.jubayedalam.e_commerce_app.viewModel.product.category.ProductCategoryViewModel
import com.jubayedalam.e_commerce_app.viewModel.product.category.ProductCategoryViewModelFactory
import com.jubayedalam.e_commerce_app.viewModel.product.item.ProductItemViewModel
import com.jubayedalam.e_commerce_app.viewModel.product.item.ProductItemViewModelFactory

class ProductsFragment : Fragment() {

    private lateinit var binding: FragmentSofaSetBinding

    private val productList = ArrayList<ProductCategoryList>()
    private lateinit var productCategoryAdapter: ProductCategoryAdapter
    private lateinit var productCategoryViewModel: ProductCategoryViewModel

    private val productItem = ArrayList<ProductItem>()
    private val productItemAdapter = ProductItemAdapter(productItem)
    private lateinit var productItemViewModel : ProductItemViewModel


    companion object {
        // Factory method to create a new instance of the fragment for stepper
        fun newInstance(): ProductsFragment {
            return ProductsFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding = FragmentSofaSetBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            imageFilter.setOnClickListener {
                startActivity(Intent(requireContext(), FilterActivity::class.java))
            }
            btnBackArrow.setOnClickListener {
                requireActivity().onBackPressed()
            }
        }

        val categoryId = arguments?.getInt("category_id")

        // set the adapter
        productCategoryAdapter = ProductCategoryAdapter(productList)
        binding.rvTabLayout.adapter = productCategoryAdapter

        if (categoryId != null){
            productCategoryWithSearchIDSetUp(categoryId)
            Toast.makeText(requireContext(), "search successfully ", Toast.LENGTH_SHORT).show()
        }else{
            productCategorySetUp()
        }
 
        initProductViewModel()
        initProductItemAdapter()

    }



    private fun initProductItemAdapter(){
        binding.rvSofaProduct.adapter = productItemAdapter

        // Set click listener for trending products details
        productItemAdapter.onItemClickListener = { product ->
            // Navigate to ProductDetailsActivity with the product ID
            val intent = Intent(requireContext(), ProductDetailsActivity::class.java)
            intent.apply {
                putExtra("product_id", product.id)
                putExtra("product_name", product.name)
                putExtra("product_price", "৳ ${product.price}")
                putExtra("product_description", product.description)
                putExtra("product_image_url", product.thumbUrl)
            }
            startActivity(intent)
        }
    }

    private fun initProductViewModel(){
        val repository = ProductItemRepository(ProductItemRetrofitInstance.apiService)
        val viewModelFactory = ProductItemViewModelFactory(repository)
        productItemViewModel = ViewModelProvider(this,viewModelFactory)[ProductItemViewModel::class.java]

        productItemViewModel.productItem.observe(viewLifecycleOwner){ response ->

            when (response) {
                is Response.Loading -> {
                    showProductItemShimmer()
                }
                is Response.Success -> {
                    response.data?.data?.results?.let { productItems ->
                        updateProductItemUI(productItems)
                        Toast.makeText(requireContext(), "success", Toast.LENGTH_SHORT).show()
                    }
                    hideProductItemShimmer()
                }
                is Response.Error -> {
                    response.errorMessage?.let { handleProductItemError(it) }
                    hideProductItemShimmer()
                    Toast.makeText(requireContext(), "failed", Toast.LENGTH_SHORT).show()
                }
            }

        }

    }

    private fun updateProductItemUI(productItems: ArrayList<ProductItem>) {
        productItem.addAll(productItems)
        productItemAdapter.setProductItem(productItems)
    }

    private fun handleProductItemError(errorMessage: String) {
        Log.e("Trending Data Failed to show", "Error: $errorMessage")
    }

    // Product list function here
    private fun productCategorySetUp() {
        val repository = ProductCategoryRepository(ProductCategoryRetrofitInstance.apiService)
        val viewModelFactory = ProductCategoryViewModelFactory(repository)
        productCategoryViewModel = ViewModelProvider(this, viewModelFactory)[ProductCategoryViewModel::class.java]

        // Observe the categories LiveData and update the UI accordingly
        productCategoryViewModel.productCategories.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Response.Loading -> {
                    // Show loading indicator
                    showCategoryShimmer()
                }

                is Response.Success -> {
                    val productsCategory = response.data?.data?.results
                    // Update UI with categories
                    if (productsCategory != null) {
                        productCategoryAdapter.setOnItemClickListener(object :
                            ProductCategoryAdapter.OnItemClickListener {
                            override fun onItemClickListener(position: Int) {
                                val category: ProductCategoryList = productsCategory[position]
                                val productArray: ArrayList<ProductCategoryList> =
                                    ArrayList<ProductCategoryList>()
                                for (i in productsCategory) {
                                    i.isSelected = i == category
                                    productArray.add(i)
                                }
                                productItemViewModel.filterProductItemsByCategory(category.id)

                            }
                        })
                        updatedProductCategory(productsCategory)
                        // Set the adapter to your RecyclerView
                    }
                    Log.d("Data Show Success", "The data is receiving successfully")
                    hideCategoryShimmer()
                }

                is Response.Error -> {
                    val errorMessage = response.errorMessage
                    // Show error message
                    Log.e("Failed to show", "error : ${errorMessage.toString()}")
                    hideCategoryShimmer()
                }
            }
        }

    }

/*    private fun productCategoryWithSearchIDSetUp(searchCategoryId: Int) {
        val repository = ProductCategoryRepository(ProductCategoryRetrofitInstance.apiService)
        val viewModelFactory = ProductCategoryViewModelFactory(repository)
        productCategoryViewModel = ViewModelProvider(this, viewModelFactory)[ProductCategoryViewModel::class.java]

        // Observe the categories LiveData and update the UI accordingly
        productCategoryViewModel.productCategories.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Response.Loading -> {
                    // Show loading indicator
                    showCategoryShimmer()
                }

                is Response.Success -> {
                    val productsCategory = response.data?.data?.results
                    // Update UI with categories
                    if (productsCategory != null) {
                        productCategoryAdapter.setOnItemClickListener(object :
                            ProductCategoryAdapter.OnItemClickListener {
                            override fun onItemClickListener(position: Int) {
                                val category: ProductCategoryList = productsCategory[position]
                                val productArray: ArrayList<ProductCategoryList> =
                                    ArrayList<ProductCategoryList>()
                                for (i in productsCategory) {
                                    i.isSelected = i.id == searchCategoryId // Highlight the category if its ID matches the received categoryId
                                    productArray.add(i)
                                }
                                productItemViewModel.filterProductItemsByCategory(category.id)
                            }
                        })
                        updatedProductCategory(productsCategory)
                        // Set the adapter to your RecyclerView
                    }
                    Log.d("Data Show Success", "The data is receiving successfully")
                    hideCategoryShimmer()
                }

                is Response.Error -> {
                    val errorMessage = response.errorMessage
                    // Show error message
                    Log.e("Failed to show", "error : ${errorMessage.toString()}")
                    hideCategoryShimmer()
                }
            }
        }
    }*/

    private fun productCategoryWithSearchIDSetUp(searchCategoryId: Int) {
        val repository = ProductCategoryRepository(ProductCategoryRetrofitInstance.apiService)
        val viewModelFactory = ProductCategoryViewModelFactory(repository)
        productCategoryViewModel = ViewModelProvider(this, viewModelFactory)[ProductCategoryViewModel::class.java]

        // Observe the categories LiveData and update the UI accordingly
        productCategoryViewModel.productCategories.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Response.Loading -> {
                    // Show loading indicator
                    showCategoryShimmer()
                }

                is Response.Success -> {
                    val productsCategory = response.data?.data?.results
                    // Update UI with categories
                    if (productsCategory != null) {
                        // Filter the products for the selected category
                        val selectedCategory = productsCategory.firstOrNull { it.id == searchCategoryId }
                        if (selectedCategory != null) {
                            val productArray = ArrayList<ProductCategoryList>()
                            for (category in productsCategory) {
                                category.isSelected = category.id == searchCategoryId
                                productArray.add(category)
                            }
                            // Update UI with filtered categories
                            updatedProductCategory(productArray)
                            // Set the adapter to your RecyclerView
                            productCategoryAdapter.setOnItemClickListener(object : ProductCategoryAdapter.OnItemClickListener {
                                override fun onItemClickListener(position: Int) {
                                    // Handle category item click if needed
                                    val category: ProductCategoryList = productsCategory[position]
                                    for (i in productsCategory) {
                                        i.isSelected = i.id == searchCategoryId // Highlight the category if its ID matches the received categoryId
                                        productArray.add(i)
                                    }
                                }
                            })
                            // Load products for the selected category
                            productItemViewModel.filterProductItemsByCategory(selectedCategory.id)
                        } else {
                            // Handle case when the selected category is not found
                            Toast.makeText(requireContext(), "Selected category not found", Toast.LENGTH_SHORT).show()
                        }
                    }
                    Log.d("Data Show Success", "The data is receiving successfully")
                    hideCategoryShimmer()
                }

                is Response.Error -> {
                    val errorMessage = response.errorMessage
                    // Show error message
                    Log.e("Failed to show", "error : ${errorMessage.toString()}")
                    hideCategoryShimmer()
                }
            }
        }
    }


    private fun updatedProductCategory(arrayList: ArrayList<ProductCategoryList>) {
        productCategoryAdapter.getUpdated(arrayList)
    }

    private fun showCategoryShimmer() {
        binding.shimmerLayoutTabLayout.startShimmer()
        binding.shimmerLayoutTabLayout.visibility = View.VISIBLE
        binding.rvTabLayout.visibility = View.INVISIBLE
    }

    private fun hideCategoryShimmer() {
        binding.shimmerLayoutTabLayout.stopShimmer()
        binding.shimmerLayoutTabLayout.visibility = View.GONE
        binding.rvTabLayout.visibility = View.VISIBLE
    }

    private fun showProductItemShimmer() {
        binding.shimmerLayoutProductItem.startShimmer()
        binding.shimmerLayoutProductItem.visibility = View.VISIBLE
        binding.rvSofaProduct.visibility = View.GONE
    }

    private fun hideProductItemShimmer() {
        binding.shimmerLayoutProductItem.stopShimmer()
        binding.shimmerLayoutProductItem.visibility = View.GONE
        binding.rvSofaProduct.visibility = View.VISIBLE
    }

}