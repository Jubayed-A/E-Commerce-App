package com.jubayedalam.e_commerce_app.ui.fragment.splashAndWelcome.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jubayedalam.e_commerce_app.databinding.FragmentThirdOnboardBinding
import com.jubayedalam.e_commerce_app.ui.activity.logInAndRegister.AccountOptionActivity

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
            startActivity(Intent(requireContext(), AccountOptionActivity::class.java))
            requireActivity().finish()
        }

        binding.btnStart.setOnClickListener {
            startActivity(Intent(requireContext(), AccountOptionActivity::class.java))
            requireActivity().finish()
        }

    }

}