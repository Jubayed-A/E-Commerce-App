package com.jubayedalam.e_commerce_app.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jubayedalam.e_commerce_app.ui.fragment.splashAndWelcome.onboarding.FirstOnboardFragment
import com.jubayedalam.e_commerce_app.ui.fragment.splashAndWelcome.onboarding.SecondOnboardFragment
import com.jubayedalam.e_commerce_app.ui.fragment.splashAndWelcome.onboarding.ThirdOnboardFragment

class OnBodyViewPagerAdapter (fragmentManager: FragmentManager, lifecycle: Lifecycle):
    FragmentStateAdapter(fragmentManager, lifecycle) {
    private val pageCount=3
    override fun getItemCount(): Int {
        return pageCount
    }

    override fun createFragment(position: Int): Fragment {
        return  when(position){
            0-> FirstOnboardFragment()
            1-> SecondOnboardFragment()
            2-> ThirdOnboardFragment()
            else-> null
        }!!
    }
}