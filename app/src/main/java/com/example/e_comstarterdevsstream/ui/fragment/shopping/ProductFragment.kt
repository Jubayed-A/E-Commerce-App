package com.example.e_comstarterdevsstream.ui.fragment.shopping

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.e_comstarterdevsstream.databinding.FragmentSofaSetBinding
import com.example.e_comstarterdevsstream.ui.activity.FilterActivity

class ProductFragment : Fragment() {

    private lateinit var binding: FragmentSofaSetBinding


    companion object {
        // Factory method to create a new instance of the fragment for stepper
        fun newInstance(): ProductFragment {
            return ProductFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding = FragmentSofaSetBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageFilter.setOnClickListener {
            startActivity(Intent(requireContext(), FilterActivity::class.java))
        }

    }

}