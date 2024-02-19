package com.example.e_comstarterdevsstream.ui.fragment.shopping.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.e_comstarterdevsstream.R
import com.example.e_comstarterdevsstream.databinding.FragmentHomeBinding
import com.example.e_comstarterdevsstream.ui.activity.CategoryActivity

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.tvCategoriesText.setOnClickListener {
            startActivity(Intent(requireContext(), CategoryActivity::class.java))
        }

        binding.tvCategoriesViewAll.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }


        val imageList = ArrayList<SlideModel>()

        imageList.add(SlideModel(R.drawable.splash_logo))
        imageList.add(SlideModel(R.drawable.first_splash))
        imageList.add(SlideModel(R.drawable.onboarding_background))

        binding.imageSlider.setImageList(imageList, scaleType = ScaleTypes.FIT)


    }

}