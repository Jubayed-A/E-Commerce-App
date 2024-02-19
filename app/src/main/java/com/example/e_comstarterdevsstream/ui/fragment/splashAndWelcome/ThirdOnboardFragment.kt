package com.example.e_comstarterdevsstream.ui.fragment.splashAndWelcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.e_comstarterdevsstream.R
import com.example.e_comstarterdevsstream.databinding.FragmentThirdOnboardBinding

class ThirdOnboardFragment : Fragment() {

    private lateinit var binding: FragmentThirdOnboardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentThirdOnboardBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSkip3.setOnClickListener {
            findNavController().navigate(R.id.action_viewPagerFragment_to_accountOptionFragment)
        }

        binding.btnStart.setOnClickListener {
            findNavController().navigate(R.id.action_viewPagerFragment_to_accountOptionFragment)
        }

    }

}