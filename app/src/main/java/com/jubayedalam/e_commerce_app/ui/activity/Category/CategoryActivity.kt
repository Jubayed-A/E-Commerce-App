package com.jubayedalam.e_commerce_app.ui.activity.Category

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jubayedalam.e_commerce_app.data.category.CategoryItems
import com.jubayedalam.e_commerce_app.databinding.ActivityCategoryBinding
import com.jubayedalam.e_commerce_app.network.category.CategoryRetrofitInstance
import com.jubayedalam.e_commerce_app.repository.category.CategoryRepository
import com.jubayedalam.e_commerce_app.ui.adapter.category.CategoryAdapter
import com.jubayedalam.e_commerce_app.utils.stateManagement.Response
import com.jubayedalam.e_commerce_app.viewModel.category.CategoryViewModel
import com.jubayedalam.e_commerce_app.viewModel.category.CategoryViewModelFactory

class CategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryBinding

    private val category = ArrayList<CategoryItems>()
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var categoryViewModel: CategoryViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnBackArrow.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
        }


        categoryAdapterSetup()
        // Call the function to set up category
        categorySetUp()




    }

    private fun categoryAdapterSetup(){
        // Set up LinearLayoutManager with vertical orientation
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvMainCategory.layoutManager = layoutManager

        // Set the adapter
        categoryAdapter = CategoryAdapter(category)
        binding.rvMainCategory.adapter = categoryAdapter
    }

    private fun categorySetUp() {
        val repository = CategoryRepository(CategoryRetrofitInstance.apiService)
        val viewModelFactory = CategoryViewModelFactory(repository)
        categoryViewModel = ViewModelProvider(this, viewModelFactory)[CategoryViewModel::class.java]

        // Observe the categories LiveData and update the UI accordingly
        categoryViewModel.categories.observe(this) { response ->
            when (response) {
                is Response.Loading -> {
                    // Show loading indicator
                    showCategoryShimmer()
                }

                is Response.Success -> {
                    val productsCategory = response.data?.data?.results
                    // Update UI with categories
                    if (productsCategory != null) {
                        categoryAdapter.setOnItemClickListener(object :
                            CategoryAdapter.OnItemClickListener {
                            override fun onItemClickListener(position: Int) {
                                /*val category: Data = Data[position]
                                val productArray: ArrayList<Data> =
                                    ArrayList<Data>()
                                for (i in productsCategory) {
                                    i.isSelected = i == category
                                    productArray.add(i)
                                }*/
                                Toast.makeText(this@CategoryActivity, "this feature is not implement yet!", Toast.LENGTH_SHORT).show()
                            }
                        })
                        updatedCategory(productsCategory)
                        // Set the adapter to your RecyclerView

                    }
                    Log.d("Data Show Success", "The data is receiving successfully")
                    Toast.makeText(this, "Data Receiving Successfully", Toast.LENGTH_SHORT).show()
                    hideCategoryShimmer()
                }

                is Response.Error -> {
                    val errorMessage = response.errorMessage
                    // Show error message
                    Log.e("Failed to show", "error : ${errorMessage.toString()}")
                    Toast.makeText(this, "Fail to load data", Toast.LENGTH_SHORT).show()
                    hideCategoryShimmer()
                }
            }
        }
    }


    private fun updatedCategory(arrayList: ArrayList<CategoryItems>) {
        categoryAdapter.getUpdated(arrayList)
    }

    private fun showCategoryShimmer() {
        binding.shimmerLayoutCategory.startShimmer()
        binding.shimmerLayoutCategory.visibility = View.VISIBLE
        binding.rvMainCategory.visibility = View.GONE
    }

    private fun hideCategoryShimmer() {
        binding.shimmerLayoutCategory.stopShimmer()
        binding.shimmerLayoutCategory.visibility = View.GONE
        binding.rvMainCategory.visibility = View.VISIBLE
    }

    private fun showCategoryProgressBar() {
        binding.categoryProgressbar.visibility = View.VISIBLE
    }
    private fun hideCategoryProgressBar() {
        binding.categoryProgressbar.visibility = View.GONE
    }

}