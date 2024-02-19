package com.example.e_comstarterdevsstream

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.e_comstarterdevsstream.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.apply {
//
//
//            setSupportActionBar(toolbar)
//
//
//
//            bottomNavigation.add(
//                CurvedBottomNavigation.Model(1, "Category", R.drawable.ic_category)
//            )
//            bottomNavigation.add(
//                CurvedBottomNavigation.Model(2, "Search", R.drawable.ic_search)
//            )
//            bottomNavigation.add(
//                CurvedBottomNavigation.Model(3, "Profile", R.drawable.ic_profile)
//            )
//
//
//            bottomNavigation.setOnClickMenuListener {
//                when(it.id){
//                    1 -> {
//                        replaceFragment(CategoryFragment())
//                    }
//                    2 -> {
//                        replaceFragment(SearchFragment())
//                    }
//                    3 -> {
//                        replaceFragment(ProfileFragment())
//                    }
//                    else -> Unit
//                }
//            }
//
//            // Default bottom navigation
//            replaceFragment(CategoryFragment())
//            bottomNavigation.show(1)
//
//        }
//
//
//
//    }
//
//    private fun replaceFragment(fragment: Fragment) {
//        supportFragmentManager
//            .beginTransaction()
//            .replace(R.id.fragmentContainer,fragment)
//            .commit()
//    }
    }
}