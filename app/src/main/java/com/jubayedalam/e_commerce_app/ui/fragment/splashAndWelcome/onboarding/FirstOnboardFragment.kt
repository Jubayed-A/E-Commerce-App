package com.jubayedalam.e_commerce_app.ui.fragment.splashAndWelcome.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jubayedalam.e_commerce_app.databinding.FragmentFirstOnboardBinding
import com.jubayedalam.e_commerce_app.ui.activity.logInAndRegister.AccountOptionActivity

class FirstOnboardFragment : Fragment() {

    private lateinit var binding: FragmentFirstOnboardBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstOnboardBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSkip.setOnClickListener {
            startActivity(Intent(requireContext(), AccountOptionActivity::class.java))
            requireActivity().finish()
        }
    }

}