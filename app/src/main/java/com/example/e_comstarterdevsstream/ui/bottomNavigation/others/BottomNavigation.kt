package com.example.e_comstarterdevsstream.ui.bottomNavigation.others

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.e_comstarterdevsstream.R
import com.example.e_comstarterdevsstream.databinding.ActivityBottomNavigationBinding
import com.example.e_comstarterdevsstream.ui.fragment.profile.ProfilesFragment
import com.example.e_comstarterdevsstream.ui.fragment.shopping.home.HomeFragment
import com.example.e_comstarterdevsstream.ui.fragment.shopping.search.SearchFragment
import com.qamar.curvedbottomnaviagtion.CurvedBottomNavigation

class BottomNavigation : AppCompatActivity() {

    private lateinit var binding: ActivityBottomNavigationBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.apply {

            // for toolbar
//            setSupportActionBar(toolbar)

            bottomNavigation.add(
                CurvedBottomNavigation.Model(1, "Category", R.drawable.ic_home)
            )
            bottomNavigation.add(
                CurvedBottomNavigation.Model(2, "Search", R.drawable.ic_search)
            )
            bottomNavigation.add(
                CurvedBottomNavigation.Model(3, "Profile", R.drawable.ic_profile)
            )

            bottomNavigation.setOnClickMenuListener {
                when (it.id) {
                    1 -> {
                        replaceFragment(HomeFragment())
                    }

                    2 -> {
                        replaceFragment(SearchFragment())
                    }

                    3 -> {
                        replaceFragment(ProfilesFragment())
                    }

                    else -> Unit
                }
            }
            // Default bottom navigation
            replaceFragment(HomeFragment())
            bottomNavigation.show(1)
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)
            .commit()
    }


}
