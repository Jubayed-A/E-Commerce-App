package com.example.e_comstarterdevsstream.ui.activity.checkout.stepper

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
class ViewPagerAdapter (fragmentManager: FragmentManager, private val fragments: List<Fragment>) :
    FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        Log.d("stepper",position.toString())
        return fragments[position]
    }

    override fun getCount(): Int {
        Log.d("stepper2",fragments.size.toString())
        return fragments.size
    }
}