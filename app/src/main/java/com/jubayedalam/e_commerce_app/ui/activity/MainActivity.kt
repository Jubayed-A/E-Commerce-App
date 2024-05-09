package com.jubayedalam.e_commerce_app.ui.activity

// this code without coroutines

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.jubayedalam.e_commerce_app.R
import com.jubayedalam.e_commerce_app.databinding.ActivityMainBinding
import com.jubayedalam.e_commerce_app.room.CartDB
import com.jubayedalam.e_commerce_app.ui.fragment.profile.ProfilesFragment
import com.jubayedalam.e_commerce_app.ui.fragment.shopping.cart.CartFragment
import com.jubayedalam.e_commerce_app.ui.fragment.shopping.home.HomeFragment
import com.jubayedalam.e_commerce_app.ui.fragment.shopping.product.ProductsFragment
import com.jubayedalam.e_commerce_app.ui.fragment.shopping.search.SearchFragment
import com.jubayedalam.e_commerce_app.ui.fragment.splashAndWelcome.LogInFragment
import com.jubayedalam.e_commerce_app.utils.sharedPreferences.SharedPreferencesManager
import com.luseen.spacenavigation.SpaceItem
import com.luseen.spacenavigation.SpaceNavigationView
import com.luseen.spacenavigation.SpaceOnClickListener

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var spaceNavigationView: SpaceNavigationView
    private lateinit var sharedPreferencesManager: SharedPreferencesManager

    companion object {
        lateinit var database: CartDB
            private set
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = Room.databaseBuilder(
            applicationContext,
            CartDB::class.java, "my-database"
        ).build()

        sharedPreferencesManager = SharedPreferencesManager(this)

        // Bottom navigation setup
        spaceNavigationView = findViewById<View>(R.id.space) as SpaceNavigationView
        spaceNavigationView.initWithSaveInstanceState(savedInstanceState)
        spaceNavigationView.shouldShowFullBadgeText(true)
        spaceNavigationView.addSpaceItem(SpaceItem("", R.drawable.ic_home))
        spaceNavigationView.addSpaceItem(SpaceItem("", R.drawable.ic_product))
        spaceNavigationView.addSpaceItem(SpaceItem("", R.drawable.ic_serach))
        spaceNavigationView.addSpaceItem(SpaceItem("", R.drawable.ic_profile))

        // Center button setup
        spaceNavigationView.setCentreButtonIcon(R.drawable.ic_cart)
        spaceNavigationView.setCentreButtonColor(ContextCompat.getColor(this, R.color.black))



        spaceNavigationView.setSpaceOnClickListener(object : SpaceOnClickListener {
            override fun onCentreButtonClick() {
                replaceFragment(CartFragment())
            }

            override fun onItemClick(itemIndex: Int, itemName: String?) {
                // Replace fragment based on clicked item
                when (itemIndex) {
                    0 -> replaceFragment(HomeFragment())
                    1 -> replaceFragment(ProductsFragment())
                    2 -> replaceFragment(SearchFragment())
                    3 -> if (sharedPreferencesManager.isLoggedIn()){
                        replaceFragment(ProfilesFragment())
                    }else{
                        replaceFragment(LogInFragment())
                    }
                }
            }

            override fun onItemReselected(itemIndex: Int, itemName: String?) {
                // If an item is reselected, navigate to its corresponding fragment
                when (itemIndex) {
                    0 -> replaceFragment(HomeFragment())
                    1 -> replaceFragment(ProductsFragment())
                    2 -> replaceFragment(SearchFragment())
                    3 -> if (sharedPreferencesManager.isLoggedIn()){
                        replaceFragment(ProfilesFragment())
                    }else{
                        replaceFragment(LogInFragment())
                    }
                }
            }
        })

        // Check if the intent has extra to navigate to a specific fragment
        if (intent.hasExtra("navigate_to_fragment")) {
            val fragmentTag = intent.getStringExtra("navigate_to_fragment")
            if (fragmentTag == "sofaset_fragment") {
                // Navigate back to SofaSetFragment
                supportFragmentManager.popBackStack("sofaset_fragment", 0)
                Log.d("MainActivity", "Fragment tag: $fragmentTag")

                // Display SofaSetFragment
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragmentContainerView2, ProductsFragment.newInstance(), "sofaset_fragment")
                transaction.addToBackStack("sofaset_fragment")
                spaceNavigationView.changeCurrentItem(1) // Update bottom nav view to indicate SofaSetFragment
                transaction.commit()
            } else {
                Toast.makeText(this, "Invalid fragment tag", Toast.LENGTH_SHORT).show()
            }
        } else {
            // Handle case where intent does not contain necessary extra
        }
    }

    // Function to replace fragments
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView2, fragment)
            .commit()
    }

    // Handle back button press
    override fun onBackPressed() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView2)

        // Replace special fragments with HomeFragment upon back press
        when (currentFragment) {
            is ProfilesFragment,
            is ProductsFragment,
            is CartFragment,
            is SearchFragment -> {
                replaceFragment(HomeFragment())
                spaceNavigationView.changeCurrentItem(0) // Change the selected item to HomeFragment
            }
            else -> super.onBackPressed()
        }
    }
}
