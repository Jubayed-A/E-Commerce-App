package com.example.e_comstarterdevsstream.ui.fragment.splashScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.e_comstarterdevsstream.R

class FirstSplashScreen : Fragment(R.layout.first_splash_screen) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val skip = view.findViewById<TextView>(R.id.btnSkip)

        skip.setOnClickListener {
            findNavController().navigate(R.id.action_splashScreen_to_afterSplashScreen)
        }

        super.onViewCreated(view, savedInstanceState)
    }

}