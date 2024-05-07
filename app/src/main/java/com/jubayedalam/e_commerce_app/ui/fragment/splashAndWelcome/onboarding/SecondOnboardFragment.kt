package com.jubayedalam.e_commerce_app.ui.fragment.splashAndWelcome.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jubayedalam.e_commerce_app.databinding.FragmentSecondOnboardBinding
import com.jubayedalam.e_commerce_app.ui.activity.logInAndRegister.AccountOptionActivity

class SecondOnboardFragment : Fragment() {

    private lateinit var binding: FragmentSecondOnboardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondOnboardBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSkip2.setOnClickListener {
            startActivity(Intent(requireContext(), AccountOptionActivity::class.java))
            requireActivity().finish()
        }
    }

}