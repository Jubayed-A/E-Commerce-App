package com.jubayedalam.e_commerce_app.ui.fragment.shopping.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.jubayedalam.e_commerce_app.R
import com.jubayedalam.e_commerce_app.data.home.category.CategoryList
import com.jubayedalam.e_commerce_app.data.home.trending.Product
import com.jubayedalam.e_commerce_app.databinding.FragmentHomeBinding
import com.jubayedalam.e_commerce_app.network.home.category.CategoryHomeRetrofitInstance
import com.jubayedalam.e_commerce_app.network.home.trending.TrendingHomeRetrofitInstance
import com.jubayedalam.e_commerce_app.repository.home.category.CategoryHomeRepository
import com.jubayedalam.e_commerce_app.repository.home.trending.TrendingHomeRepository
import com.jubayedalam.e_commerce_app.room.CartEntity
import com.jubayedalam.e_commerce_app.ui.activity.Category.CategoryActivity
import com.jubayedalam.e_commerce_app.ui.activity.productDetials.ProductDetailsActivity
import com.jubayedalam.e_commerce_app.ui.adapter.home.category.CategoryHomeAdapter
import com.jubayedalam.e_commerce_app.ui.adapter.home.trending.TrendingHomeAdapter
import com.jubayedalam.e_commerce_app.utils.stateManagement.Response
import com.jubayedalam.e_commerce_app.viewModel.cart.CartViewModel
import com.jubayedalam.e_commerce_app.viewModel.home.category.CategoryHomeViewModel
import com.jubayedalam.e_commerce_app.viewModel.home.category.CategoryHomeViewModelFactory
import com.jubayedalam.e_commerce_app.viewModel.home.imageSlider.SliderViewModel
import com.jubayedalam.e_commerce_app.viewModel.home.trending.TrendingHomeViewModel
import com.jubayedalam.e_commerce_app.viewModel.home.trending.TrendingHomeViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private var nextUrl: String? = null
    private var isLoadingTrending = false

    private val sliderViewModel: SliderViewModel by viewModels()

    private val category = ArrayList<CategoryList>()
    private val categoryHomeAdapter = CategoryHomeAdapter(category)
    private lateinit var categoryViewModel: CategoryHomeViewModel

    private val trendingList = ArrayList<Product>()
    private val trendingHomeAdapter = TrendingHomeAdapter(trendingList)
    private lateinit var trendingHomeViewModel: TrendingHomeViewModel

    private lateinit var cartViewModel: CartViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            tvCategoriesViewAll.setOnClickListener {
                startActivity(Intent(requireContext(), CategoryActivity::class.java))
            }
            imageCart.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_cartFragment2)
            }
            tvTrendingViewAll.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_sofaSetFragment)
            }
        }

        // new way to call api for image slider
        sliderViewModel.thumbnailUrls.observe(viewLifecycleOwner) { thumbnailUrls ->
            if (thumbnailUrls.isNotEmpty()) {
                setupImageSlider(thumbnailUrls)
            }
        }

        // initial all viewModel code
        initializeViewModels()

        // set the adapter
        binding.rvCategory.adapter = categoryHomeAdapter
        // get the data
//        categoryHomeSetUp()
        setupCategoryObservers()


        binding.rvTrendingProduct.adapter = trendingHomeAdapter

        // Set click listener for trending products details
        trendingHomeAdapter.onItemClickListener = { product ->
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

        trendingHomeAdapter.onAddToCartClick = { product ->
            addToCart(product)
        }

//        trendingHomeSetUp()
        setupTrendingObservers()

        // for paging code
        /*        binding.rvTrendingProduct.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                        super.onScrollStateChanged(recyclerView, newState)
                    }

                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                         val layoutManager : LinearLayoutManager? = recyclerView.layoutManager as LinearLayoutManager?
                        if (layoutManager != null){
                            val visibleItemCount: Int = layoutManager.childCount
                            val totalItemCount: Int = layoutManager.itemCount
                            val firstVisibleItemPosition: Int = layoutManager.findFirstVisibleItemPosition()
                            if (!isLoadingTrending && nextUrl != null) {
                                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                                    && firstVisibleItemPosition >= 0
                                ) {
                                    // Launch a coroutine to call the suspend function
                                    CoroutineScope(Dispatchers.Main).launch {
                                        trendingHomeNextUrl(nextUrl!!)
                                    }
                                }
                            }
                        }
                    }
                })*/


    }

    private fun addToCart(product: Product) {
        val existingProduct = findProductInCart(product)
        if (existingProduct != null) {
            // Product already exists in the cart, increment its quantity
            existingProduct.quantity++
            // Update the cart in the ViewModel
            cartViewModel.updateProduct(existingProduct)
            Toast.makeText(requireContext(), "Product quantity increased", Toast.LENGTH_SHORT).show()
        } else {
            // Product doesn't exist in the cart, add it to the cart
            val cartEntity = CartEntity(0, product.id.toString(), product.name, product.price,
                product.thumbUrl, 1)
            // Insert the product into the Room database using the DAO
            insertProductIntoDatabase(cartEntity)
            Toast.makeText(requireContext(), "Product added to cart", Toast.LENGTH_SHORT).show()
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
        CoroutineScope(Dispatchers.IO).launch {
            cartViewModel.insertProduct(productEntity)
        }
    }


    // image slider code
    private fun setupImageSlider(thumbnailUrls: List<String>) {
        val imageList = ArrayList<SlideModel>()
        thumbnailUrls.forEach { url ->
            if (url.isNotEmpty()) {
                imageList.add(SlideModel(url))
            } else {
                Log.e("ImageSlider", "Invalid URL: $url")
            }
        }
        binding.imageSlider.setImageList(imageList, scaleType = ScaleTypes.CENTER_CROP)
    }


    // new code
    // this code work (shimmer effects and click lister)

    private fun initializeViewModels() {
        // Initialize CategoryHomeViewModel
        val categoryRepository = CategoryHomeRepository(CategoryHomeRetrofitInstance.apiService)
        val categoryViewModelFactory = CategoryHomeViewModelFactory(categoryRepository)
        categoryViewModel = ViewModelProvider(
            this,
            categoryViewModelFactory
        )[CategoryHomeViewModel::class.java]

        // Initialize TrendingHomeViewModel
        val trendingRepository = TrendingHomeRepository(TrendingHomeRetrofitInstance.apiService)
        val trendingViewModelFactory = TrendingHomeViewModelFactory(trendingRepository)
        trendingHomeViewModel = ViewModelProvider(this, trendingViewModelFactory)[TrendingHomeViewModel::class.java]


        cartViewModel = ViewModelProvider(this)[CartViewModel::class.java]
    }

    private fun setupCategoryObservers() {
        categoryViewModel.categories.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Response.Loading -> {
                    showCategoryShimmer()
                }
                is Response.Success -> {
                    response.data?.data?.results?.let { categories ->
                        updateCategoryUI(categories)
                    }
                    hideCategoryShimmer()
                }
                is Response.Error -> {
                    response.errorMessage?.let { handleCategoryError(it) }
                    hideCategoryShimmer()
                }
            }
        }
    }

    private fun setupTrendingObservers() {
        trendingHomeViewModel.trendingHomeProduct.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Response.Loading -> {
                    showTrendingShimmer()
                }
                is Response.Success -> {
                    response.data?.data?.results?.let { trendingProducts ->
                        updateTrendingUI(trendingProducts)
                    }
                    hideTrendingShimmer()
                }
                is Response.Error -> {
                    response.errorMessage?.let { handleTrendingError(it) }
                    hideTrendingShimmer()
                }
            }
        }
    }

    private fun showCategoryShimmer() {
        binding.shimmerLayoutCategoryHome.startShimmer()
        binding.shimmerLayoutCategoryHome.visibility = View.VISIBLE
        binding.rvCategory.visibility = View.INVISIBLE
    }

    private fun hideCategoryShimmer() {
        binding.shimmerLayoutCategoryHome.stopShimmer()
        binding.shimmerLayoutCategoryHome.visibility = View.GONE
        binding.rvCategory.visibility = View.VISIBLE
    }

    private fun showTrendingShimmer() {
        binding.shimmerLayoutTrendingHome.startShimmer()
        binding.shimmerLayoutTrendingHome.visibility = View.VISIBLE
        binding.rvTrendingProduct.visibility = View.INVISIBLE
    }

    private fun hideTrendingShimmer() {
        binding.shimmerLayoutTrendingHome.stopShimmer()
        binding.shimmerLayoutTrendingHome.visibility = View.GONE
        binding.rvTrendingProduct.visibility = View.VISIBLE
    }

    private fun updateCategoryUI(categories: ArrayList<CategoryList>) {
        categoryHomeAdapter.setOnItemClickListener(object : CategoryHomeAdapter.OnItemClickListener {
            override fun onItemClickListener(position: Int) {
                val category: CategoryList = categories[position]
                val categoryArray: ArrayList<CategoryList> = ArrayList()
                for (i in categories) {
                    i.isSelected = i == category
                    categoryArray.add(i)
                }
                categoryViewModel.setSelectedCategory(category)
                // Filter trending products by selected category
                trendingHomeViewModel.filterTrendingProductsByCategory(category.id)
            }
        })
        updatedCategoryHome(categories)
    }

    private fun updateTrendingUI(trendingProducts: ArrayList<Product>) {
        trendingList.addAll(trendingProducts)
        updatedTrendingHomeProduct(trendingProducts)
    }

    private fun handleCategoryError(errorMessage: String) {
        Log.e("Category Data Failed to show", "error : $errorMessage")
        // Show error message
    }

    private fun handleTrendingError(errorMessage: String) {
        Log.e("Trending Data Failed to show", "Error: $errorMessage")
        // Show error message
    }

    private fun updatedCategoryHome(arrayList: ArrayList<CategoryList>) {
        categoryHomeAdapter.getUpdated(arrayList)
    }

    private fun updatedTrendingHomeProduct(trendingHomeProducts: ArrayList<Product>) {
        trendingHomeAdapter.getUpdated(trendingHomeProducts)
    }




    // old code


    /*    private fun categoryHomeSetUp() {
            val categoryRepository = CategoryHomeRepository(CategoryHomeRetrofitInstance.apiService)
            val viewModelFactory = CategoryHomeViewModelFactory(categoryRepository)
            categoryViewModel =
                ViewModelProvider(this, viewModelFactory)[CategoryHomeViewModel::class.java]

            // Observe the categories LiveData and update the UI accordingly
            categoryViewModel.categories.observe(viewLifecycleOwner) { response ->
                when (response) {
                    is Response.Loading -> {
                        // Show loading indicator
                        showCategoryHomeProgressBar()
                    }

                    is Response.Success -> {
                        val categories = response.data?.data?.results
                        // Update UI with categories
                        if (categories != null) {
                            categoryHomeAdapter.setOnItemClickListener(object :
                                CategoryHomeAdapter.OnItemClickListener {
                                override fun onItemClickListener(position: Int) {
                                    val category: CategoryList = categories[position]
                                    val categoryArray: ArrayList<CategoryList> =
                                        ArrayList<CategoryList>()
                                    for (i in categories) {
                                        i.isSelected = i == category
                                        categoryArray.add(i)
                                    }

                                    // Filter trending products based on the selected category
                                    filterTrendingProductsByCategory(category.id)

                                }
                            })
                            updatedCategoryHome(categories)
                            // Set the adapter to your RecyclerView

                        }
                        Log.d("Category Data Show Success", "The data is receiving successfully")
                        hideCategoryHomeProgressBar()
                    }

                    is Response.Error -> {
                        val errorMessage = response.errorMessage
                        // Show error message
                        Log.e("Category Data Failed to show", "error : ${errorMessage.toString()}")
                        hideCategoryHomeProgressBar()
                    }
                }
            }

        }*/

    /*    private fun trendingHomeSetUp() {
            val categoryId = 148
            val trendingRepository = TrendingHomeRepository(TrendingHomeRetrofitInstance.getTrendingHomeApiService(categoryId))
            val viewModelFactory = TrendingHomeViewModelFactory(trendingRepository)
            trendingHomeViewModel =
                ViewModelProvider(this, viewModelFactory)[TrendingHomeViewModel::class.java]

            trendingList.clear()

            // Observe the categories LiveData and update the UI accordingly
            trendingHomeViewModel.trendingHomeProduct.observe(viewLifecycleOwner) { response ->
                when (response) {
                    is Response.Loading -> {
                        // Show loading indicator
                        showTrendingHomeProgressBar()
                    }

                    is Response.Success -> {
                        val categories = response.data?.data?.results
                        val nextUrl = response.data?.data?.next
                        // Update UI with categories
                        if (categories != null) {
                            trendingList.addAll(categories)
                            updatedTrendingHomeProduct(categories)
                            // Set the adapter to your RecyclerView
                        }
                        if (nextUrl != null) {
                            this.nextUrl = nextUrl
                        }
                        Log.d(
                            "Trending Data Show Success",
                            "The TrendingProducts data is receiving successfully"
                        )
                        hideTrendingHomeProgressBar()
                    }

                    is Response.Error -> {
                        val errorMessage = response.errorMessage
                        // Show error message
                        Log.e("Trending Data Failed to show", "error : ${errorMessage.toString()}")
                        hideTrendingHomeProgressBar()
                    }
                }
            }

            trendingHomeViewModel.getNextTrendingProduct(categoryId, ).observe(viewLifecycleOwner) { response ->
                // Handle next trending product response
                when (response) {
                    is Response.Loading -> {
                        // Show loading indicator
                    }

                    is Response.Success -> {
                        val categories = response.data?.data?.results
                        val nextUrl = response.data?.data?.next
                        // Update UI with next set of categories
                        if (categories != null) {
                            trendingList.addAll(categories)
                            updatedTrendingHomeProduct(categories)
                            // Set the adapter to your RecyclerView
                        }
                        if (nextUrl != null) {
                            this.nextUrl = nextUrl
                        }
                        hideTrendingHomeProgressBar()
                    }

                    is Response.Error -> {
                        val errorMessage = response.errorMessage
                        // Show error message
                        hideTrendingHomeProgressBar()
                    }
                }
            }

        }*/


    /*private fun categoryHomeSetUp() {
        val categoryRepository = CategoryHomeRepository(CategoryHomeRetrofitInstance.apiService)
        val viewModelFactory = CategoryHomeViewModelFactory(categoryRepository)
        categoryViewModel =
            ViewModelProvider(this, viewModelFactory)[CategoryHomeViewModel::class.java]

        // Observe the categories LiveData and update the UI accordingly
        categoryViewModel.categories.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Response.Loading -> {
                    // Show loading indicator
                    showCategoryHomeProgressBar()
                    binding.shimmerLayoutCategoryHome.startShimmer()
                }

                is Response.Success -> {
                    val categories = response.data?.data?.results
                    // Update UI with categories
                    if (categories != null) {
                        categoryHomeAdapter.setOnItemClickListener(object :
                            CategoryHomeAdapter.OnItemClickListener {
                            override fun onItemClickListener(position: Int) {
                                val category: CategoryList = categories[position]
                                val categoryArray: ArrayList<CategoryList> =
                                    ArrayList<CategoryList>()
                                for (i in categories) {
                                    i.isSelected = i == category
                                    categoryArray.add(i)
                                }

                                // Set the selected category in the CategoryViewModel
                                categoryViewModel.setSelectedCategory(category)
                            }
                        })
                        updatedCategoryHome(categories)
                        // Set the adapter to your RecyclerView

                    }
                    Log.d("Category Data Show Success", "The data is receiving successfully")
                    hideCategoryHomeProgressBar()
                    binding.shimmerLayoutCategoryHome.stopShimmer()
                    binding.shimmerLayoutCategoryHome.visibility = View.GONE
                    binding.rvCategory.visibility = View.VISIBLE
                }

                is Response.Error -> {
                    val errorMessage = response.errorMessage
                    // Show error message
                    Log.e("Category Data Failed to show", "error : ${errorMessage.toString()}")
                    hideCategoryHomeProgressBar()
                    binding.shimmerLayoutCategoryHome.stopShimmer()
                }
            }
        }
    }

    private fun trendingHomeSetUp() {
        val trendingRepository = TrendingHomeRepository(TrendingHomeRetrofitInstance.apiService)
        val viewModelFactory = TrendingHomeViewModelFactory(trendingRepository)
        trendingHomeViewModel =
            ViewModelProvider(this, viewModelFactory)[TrendingHomeViewModel::class.java]

        trendingList.clear()

        // Observe the trending products LiveData and update the UI accordingly
        trendingHomeViewModel.trendingHomeProduct.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Response.Loading -> {
                    // Show loading indicator
                    showTrendingHomeProgressBar()
                    binding.shimmerLayoutTrendingHome.startShimmer()
                }

                is Response.Success -> {
                    val trendingProducts = response.data?.data?.results
                    val nextUrl = response.data?.data?.next
                    // Update UI with trending products
                    if (trendingProducts != null) {
                        trendingList.addAll(trendingProducts)
                        updatedTrendingHomeProduct(trendingProducts)
                        // Set the adapter to your RecyclerView
                    }
                    if (nextUrl != null) {
                        this.nextUrl = nextUrl
                    }
                    Log.d("Trending Data Show Success", "Trending products data received successfully")
                    hideTrendingHomeProgressBar()
                    binding.shimmerLayoutTrendingHome.stopShimmer()
                    binding.shimmerLayoutTrendingHome.visibility = View.GONE
                    binding.rvTrendingProduct.visibility = View.VISIBLE
                }

                is Response.Error -> {
                    val errorMessage = response.errorMessage
                    // Show error message
                    Log.e("Trending Data Failed to show", "Error: ${errorMessage.toString()}")
                    hideTrendingHomeProgressBar()
                    binding.shimmerLayoutTrendingHome.stopShimmer()
                    binding.shimmerLayoutTrendingHome.visibility = View.GONE
                }
            }
        }

        // Observe the selected category LiveData and trigger filtering of trending products
        categoryViewModel.selectedCategory.observe(viewLifecycleOwner) { selectedCategory ->
            selectedCategory?.let {
                trendingHomeViewModel.filterTrendingProductsByCategory(selectedCategory.id)
            }
        }
    }*/


    /*    private fun loadNextTrendingProducts(categoryId: Int, nextUrl: String) {
        trendingHomeViewModel.getNextTrendingProduct(categoryId, nextUrl)
    }*/
    private fun filterTrendingProductsByCategory(categoryId: Int) {
        /*// Filter the trending products to show only those belonging to the selected category
        val filteredProducts = trendingList.filter { it.categoryId == categoryId }
        updatedTrendingHomeProduct(filteredProducts)*/
        trendingHomeViewModel.filterTrendingProductsByCategory(categoryId)
    }


/*    private suspend fun trendingHomeNextUrl(url: String) {
        val trendingRepository = TrendingHomeRepository(TrendingHomeRetrofitInstance.apiService)
        val viewModelFactory = TrendingHomeViewModelFactory(trendingRepository)
        trendingHomeViewModel =
            ViewModelProvider(this, viewModelFactory)[TrendingHomeViewModel::class.java]

        // Observe the categories LiveData and update the UI accordingly
        nextUrl?.let {
            trendingHomeViewModel.getNextTrendingProduct(it)
                .observe(viewLifecycleOwner) { response ->
                    when (response) {
                        is Response.Success -> {
                            val categories = response.data?.data?.results
                            val nextUrl = response.data?.data?.next
                            // Update UI with categories
                            if (categories != null) {
                                trendingList.addAll(categories)
                                updatedTrendingHomeProduct(trendingList)
                                // Set the adapter to your RecyclerView
                            }
                            if (nextUrl != null) {
                                this.nextUrl = nextUrl
                            } else {
                                this.nextUrl = null
                            }
                            Log.d("Data Show Success", "The data is receiving successfully")
                        }

                        is Response.Error -> {
                            val errorMessage = response.errorMessage
                            // Show error message
                            Log.e("Failed to show", "error : ${errorMessage.toString()}")
                        }

                        else -> {
                            Unit
                        }
                    }
                }
        }

    }*/



    private fun showTrendingHomeProgressBar() {
        binding.trendingProductProgressbar.visibility = View.VISIBLE
    }

    private fun hideTrendingHomeProgressBar() {
        binding.trendingProductProgressbar.visibility = View.GONE
    }


    private fun showCategoryHomeProgressBar() {
        binding.categoryHomeProgressbar.visibility = View.VISIBLE
    }

    private fun hideCategoryHomeProgressBar() {
        binding.categoryHomeProgressbar.visibility = View.GONE
    }

}