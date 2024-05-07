package com.jubayedalam.e_commerce_app.ui.fragment.splashAndWelcome.accountOption

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jubayedalam.e_commerce_app.databinding.ActivityAccountOptionBinding

class AccountOptionFragment : Fragment() {

    private lateinit var binding: ActivityAccountOptionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ActivityAccountOptionBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}