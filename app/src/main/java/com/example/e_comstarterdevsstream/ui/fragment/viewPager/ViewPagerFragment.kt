package com.example.e_comstarterdevsstream.ui.fragment.viewPager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.e_comstarterdevsstream.R
import com.example.e_comstarterdevsstream.ui.fragment.splashScreen.FirstSplashScreen
import com.example.e_comstarterdevsstream.ui.fragment.splashScreen.SecondSplashScreen
import com.example.e_comstarterdevsstream.ui.fragment.splashScreen.ThirdSplashScreen

class ViewPagerFragment : Fragment() {

    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_view_pager, container, false)

        viewPager = view.findViewById(R.id.viewPager)

        val fragmentList = arrayListOf<Fragment>(
            FirstSplashScreen(),
            SecondSplashScreen(),
            ThirdSplashScreen()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        viewPager.adapter = adapter

        return view
    }
}
