package com.example.e_comstarterdevsstream.ui.activity.checkout.fragments

import android.os.Bundle
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.e_comstarterdevsstream.databinding.FragmentCheckoutInfoBinding

class CheckoutInfoFragment : Fragment() {

    private lateinit var binding: FragmentCheckoutInfoBinding


    companion object {
        // Factory method to create a new instance of the fragment for stepper
        fun newInstance(): CheckoutInfoFragment {
            return CheckoutInfoFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}