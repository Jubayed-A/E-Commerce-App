package com.example.e_comstarterdevsstream.ui.fragment.splashAndWelcome

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.e_comstarterdevsstream.R
import com.example.e_comstarterdevsstream.databinding.FragmentSpalshScreenBinding

class SplashScreenFragment : Fragment() {

    private lateinit var binding: FragmentSpalshScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSpalshScreenBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler(Looper.myLooper()!!).postDelayed({
            navigateToNextDestination()
        }, 2000)
    }

    private fun navigateToNextDestination() {
        if (isOnboardingFinished()) {
            findNavController().navigate(R.id.action_splashScreenFragment_to_accountOptionFragment)
        } else {
            findNavController().navigate(R.id.action_splashScreenFragment_to_viewPagerFragment)
            setOnboardingFinished()
        }
    }

    private fun isOnboardingFinished(): Boolean {
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finish", false)
    }

    private fun setOnboardingFinished() {
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putBoolean("Finish", true)
            apply()
        }
    }
}
