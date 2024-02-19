package com.example.e_comstarterdevsstream.ui.fragment.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.e_comstarterdevsstream.databinding.FragmentProfilesBinding
import com.example.e_comstarterdevsstream.ui.activity.profile.EditProfilesActivity

class ProfilesFragment : Fragment() {

    private lateinit var binding: FragmentProfilesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfilesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.editProfile.setOnClickListener {
            startActivity(Intent(requireContext(), EditProfilesActivity::class.java))
        }

    }

}