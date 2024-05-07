/*package com.devsstore.devsstore.ui.fragment.splashAndWelcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.CompositePageTransformer
import com.devsstore.devsstore.databinding.FragmentViewPagerBinding
import com.devsstore.devsstore.ui.adapter.OnBodyViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class ViewPagerFragment : Fragment() {

    private lateinit var binding: FragmentViewPagerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentViewPagerBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setPager()
    }

    private fun setPager() {
        val pager = binding.viewPager
        val tabLayout = binding.tabLayout
        val pagerAdapter =
            OnBodyViewPagerAdapter(childFragmentManager, viewLifecycleOwner.lifecycle)
        val compositePageTransformer = CompositePageTransformer()
        pager.clipToPadding = false
        pager.clipChildren = false
        pager.offscreenPageLimit = 3
        pager.setPageTransformer(compositePageTransformer)
        pager.adapter = pagerAdapter

        TabLayoutMediator(tabLayout, pager) { tab, position ->
            // add some click action according to page position
        }.attach()
    }

}*/



package com.jubayedalam.e_commerce_app.ui.fragment.splashAndWelcome

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.CompositePageTransformer
import com.jubayedalam.e_commerce_app.databinding.FragmentViewPagerBinding
import com.jubayedalam.e_commerce_app.ui.adapter.OnBodyViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class ViewPagerFragment : AppCompatActivity() {

    private lateinit var binding: FragmentViewPagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentViewPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setPager()
    }

    private fun setPager() {
        val pager = binding.viewPager
        val tabLayout = binding.tabLayout
        val pagerAdapter =
            OnBodyViewPagerAdapter(supportFragmentManager, this.lifecycle)
        val compositePageTransformer = CompositePageTransformer()
        pager.clipToPadding = false
        pager.clipChildren = false
        pager.offscreenPageLimit = 3
        pager.setPageTransformer(compositePageTransformer)
        pager.adapter = pagerAdapter

        TabLayoutMediator(tabLayout, pager) { tab, position ->
            // add some click action according to page position
        }.attach()
    }

}