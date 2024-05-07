package com.jubayedalam.e_commerce_app.ui.fragment.profile

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.jubayedalam.e_commerce_app.R
import com.jubayedalam.e_commerce_app.databinding.FragmentProfilesBinding
import com.jubayedalam.e_commerce_app.network.profile.ProfileRetrofitInstance
import com.jubayedalam.e_commerce_app.repository.profile.ProfileRepository
import com.jubayedalam.e_commerce_app.ui.activity.MainActivity
import com.jubayedalam.e_commerce_app.ui.activity.address.ShippingAddressActivity
import com.jubayedalam.e_commerce_app.ui.activity.favourit.MyFavouritesActivity
import com.jubayedalam.e_commerce_app.ui.activity.orders.OrderHistoryActivity
import com.jubayedalam.e_commerce_app.ui.activity.profile.EditProfilesActivity
import com.jubayedalam.e_commerce_app.ui.activity.saveCards.MySavedCardsActivity
import com.jubayedalam.e_commerce_app.ui.activity.voucher.VouchersActivity
import com.jubayedalam.e_commerce_app.utils.sharedPreferences.SharedPreferencesManager
import com.jubayedalam.e_commerce_app.utils.stateManagement.Response
import com.jubayedalam.e_commerce_app.viewModel.profile.ProfileViewModel
import com.jubayedalam.e_commerce_app.viewModel.profile.ProfileViewModelFactory


class ProfilesFragment : Fragment() {

    private lateinit var binding: FragmentProfilesBinding
    private lateinit var viewModel: ProfileViewModel

    private lateinit var sharedPreferencesManager: SharedPreferencesManager


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

            backArrow.setOnClickListener {
                requireActivity().onBackPressed()
            }

            myOrderSection.setOnClickListener {
                startActivity(Intent(requireContext(), OrderHistoryActivity::class.java))
            }

            myFavouritesSection.setOnClickListener {
                startActivity(Intent(requireContext(), MyFavouritesActivity::class.java))
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

        }


        sharedPreferencesManager = SharedPreferencesManager(requireContext())

        binding.logoutSection.setOnClickListener {
            val dialog = Dialog(requireContext())
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.custom_alert_dialog)
            dialog.setCancelable(false)

            val btnCancel = dialog.findViewById<TextView>(R.id.btnCancel)
            val btnLogout = dialog.findViewById<TextView>(R.id.btnLogout)

            btnCancel.setOnClickListener {
                dialog.dismiss()
            }
            btnLogout.setOnClickListener {
                sharedPreferencesManager.clearToken()
                startActivity(Intent(requireContext(), MainActivity::class.java))
                requireActivity().finish()
                dialog.dismiss()
            }

            dialog.show()

        }

        val repository = ProfileRepository(ProfileRetrofitInstance.apiService)
        val factory = ProfileViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[ProfileViewModel::class.java]

        displayUserInfo()

    }


    private fun displayUserInfo() {
        viewModel.userLiveData.observe(viewLifecycleOwner, Observer { response ->
            val user = response.data?.data
            when (response) {
                is Response.Success -> {
//                    val user = response.data?.data
                    if (user != null) {
                        // Update UI with user data
                        binding.tvPersonName.text = "${user.first_name} ${user.last_name}"
                        binding.tvPersonMail.text = user.email
                        // Load image using Glide or any other image loading library
                        Glide.with(requireContext())
                            .load(user.image_url)
                            .into(binding.imageProfile)
                        Log.d("User info", user.toString())
                    }
                }

                is Response.Error -> {
                    // Handle error
                    Toast.makeText(requireContext(), "Failed to load user data", Toast.LENGTH_SHORT)
                        .show()
                    Log.e("Profile Data", "Error: ${response.errorMessage}")
                }

                else -> {
                    // Handle other cases if needed
                    Toast.makeText(requireContext(), "Unexpected response", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            if (user != null) {
                binding.editProfile.setOnClickListener {
                    val intent = Intent(requireContext(), EditProfilesActivity::class.java)
                    intent.apply {
                        putExtra("profile_id", user.id)
                        putExtra("first_name", user.first_name)
                        putExtra("last_name", user.last_name)
                        putExtra("email", user.email)
                        putExtra("mobile", user.mobile)
                        putExtra("image", user.image_url.toString())
                    }
                    startActivity(intent)
                }
            }


        })

        val token = "Token ${sharedPreferencesManager.getToken()}"

        viewModel.getUser(token)
    }

}