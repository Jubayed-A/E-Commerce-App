package com.example.e_comstarterdevsstream.ui.fragment.logInAndRegister

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.e_comstarterdevsstream.R
import com.example.e_comstarterdevsstream.databinding.FragmentLogInBinding

class LogInFragment : Fragment() {

    private lateinit var binding: FragmentLogInBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLogInBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textForgotPassword.setOnClickListener {
            findNavController().navigate(R.id.action_logInFragment_to_resetPasswordFragment)
        }

        binding.textDontHaveAccount.setOnClickListener {
            findNavController().navigate(R.id.action_logInFragment_to_registerFragment)
        }

    }

}