package com.example.e_comstarterdevsstream.ui.fragment.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.e_comstarterdevsstream.databinding.FragmentProfilesBinding
import com.example.e_comstarterdevsstream.ui.activity.MyFavouritesProductsActivity
import com.example.e_comstarterdevsstream.ui.activity.MySavedCardsActivity
import com.example.e_comstarterdevsstream.ui.activity.ReviewsActivity
import com.example.e_comstarterdevsstream.ui.activity.ShippingAddressActivity
import com.example.e_comstarterdevsstream.ui.activity.VouchersActivity
import com.example.e_comstarterdevsstream.ui.activity.orders.OrderHistoryActivity
import com.example.e_comstarterdevsstream.ui.activity.profile.EditProfilesActivity

class ProfilesFragment : Fragment() {

    private lateinit var binding: FragmentProfilesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfilesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            editProfile.setOnClickListener {
                startActivity(Intent(requireContext(), EditProfilesActivity::class.java))
            }

            myOrderSection.setOnClickListener {
                startActivity(Intent(requireContext(), OrderHistoryActivity::class.java))
            }

            myFavouritesSection.setOnClickListener {
                startActivity(Intent(requireContext(), MyFavouritesProductsActivity::class.java))
            }

            shippingAddressSection.setOnClickListener {
                startActivity(Intent(requireContext(), ShippingAddressActivity::class.java))
            }

            myCardSection.setOnClickListener {
                startActivity(Intent(requireContext(), MySavedCardsActivity::class.java))
            }

            voucherSection.setOnClickListener {
                startActivity(Intent(requireContext(), VouchersActivity::class.java))
            }

            logoutSection.setOnClickListener {
                startActivity(Intent(requireContext(), ReviewsActivity::class.java))
            }


        }
    }

}