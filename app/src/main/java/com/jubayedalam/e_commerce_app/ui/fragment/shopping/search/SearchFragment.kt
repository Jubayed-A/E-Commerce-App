package com.jubayedalam.e_commerce_app.ui.fragment.shopping.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.jubayedalam.e_commerce_app.R
import com.jubayedalam.e_commerce_app.data.product.category.ProductCategoryList
import com.jubayedalam.e_commerce_app.databinding.FragmentSearchBinding
import com.jubayedalam.e_commerce_app.network.product.category.ProductCategoryRetrofitInstance
import com.jubayedalam.e_commerce_app.repository.product.category.ProductCategoryRepository
import com.jubayedalam.e_commerce_app.ui.adapter.search.SearchAdapter
import com.jubayedalam.e_commerce_app.ui.fragment.shopping.product.ProductsFragment
import com.jubayedalam.e_commerce_app.utils.stateManagement.Response
import com.jubayedalam.e_commerce_app.viewModel.product.category.ProductCategoryViewModel
import com.jubayedalam.e_commerce_app.viewModel.product.category.ProductCategoryViewModelFactory


class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding

    private val productList = ArrayList<ProductCategoryList>()
    private lateinit var searchAdapter : SearchAdapter

    private val viewModel: ProductCategoryViewModel by viewModels {
        ProductCategoryViewModelFactory(
            ProductCategoryRepository(ProductCategoryRetrofitInstance.apiService)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBackArrow.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.searchIcon.setOnClickListener {
            val query = binding.searchBar.text.toString()
            if (query.isNotEmpty()){
                viewModel.searchCategory(query)
                binding.rvSearchView.visibility = View.VISIBLE
            }else{
                searchAdapter.getUpdated(productList)
                Toast.makeText(requireContext(), "Enter a category name to search", Toast.LENGTH_SHORT).show()
            }
        }

        // set the adapter
        searchAdapter = SearchAdapter(productList)
        binding.rvSearchView.adapter = searchAdapter
        productCategorySetUp()

    }

    // Product list function here
    private fun productCategorySetUp() {
        // Observe the categories LiveData and update the UI accordingly
        viewModel.productCategories.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Response.Loading -> {
                    // Show loading indicator
//                    showCategoryShimmer()
                }

                is Response.Success -> {
                    val productsCategory = response.data?.data?.results
                    // Update UI with categories

                    /*if (productsCategory != null) {
                        searchAdapter.setOnItemClickListener(object :
                            SearchAdapter.OnItemClickListener {
                            override fun onItemClickListener(position: Int) {
                                replaceFragment(ProductsFragment(), productsCategory[position].id)
                            }
                        })
                        updatedProductCategory(productsCategory)
                        // Set the adapter to your RecyclerView
                    }*/

                    if (productsCategory != null && productsCategory.isNotEmpty()) {
                        searchAdapter.setOnItemClickListener(object : SearchAdapter.OnItemClickListener {
                            override fun onItemClickListener(position: Int) {
                                if (position >= 0 && position < productsCategory.size) {
                                    // Ensure position is within the valid range
                                    replaceFragment(ProductsFragment(), productsCategory[position].id)
                                }
                            }
                        })
                        updatedProductCategory(productsCategory)
                        // Set the adapter to your RecyclerView
                    }

                    Log.d("Data Show Success", "The data is receiving successfully")
//                    hideCategoryShimmer()
                }

                is Response.Error -> {
                    val errorMessage = response.errorMessage
                    // Show error message
                    Log.e("Failed to show", "error : ${errorMessage.toString()}")
//                    hideCategoryShimmer()
                }
            }
        }

    }

    private fun updatedProductCategory(arrayList: ArrayList<ProductCategoryList>) {
        searchAdapter.getUpdated(arrayList)
    }

    private fun replaceFragment(fragment: Fragment, categoryId : Int) {

        val bundle = Bundle()
        bundle.putInt("category_id", categoryId)
        fragment.arguments = bundle

        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView2, fragment)
            .commit()
    }

}