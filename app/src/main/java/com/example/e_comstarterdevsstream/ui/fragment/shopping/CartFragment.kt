package com.example.e_comstarterdevsstream.ui.fragment.shopping

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.e_comstarterdevsstream.databinding.FragmentCartBinding
import com.example.e_comstarterdevsstream.ui.activity.checkout.stepper.CheckoutActivity


class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            btnCheckOut.setOnClickListener{
                startActivity(Intent(requireContext(), CheckoutActivity::class.java))
            }



        }


    }

}
