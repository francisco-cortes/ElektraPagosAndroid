package com.elektra.ektp.ektphome.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.elektra.ektp.R
import com.elektra.ektp.databinding.FragmentEktpHomeProfileBinding
import com.elektra.ektp.ektphome.viewmodel.EKTPHomeProfileViewModel

class EKTPHomeProfileFragment : Fragment() {

    private lateinit var binding: FragmentEktpHomeProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentEktpHomeProfileBinding>(inflater,R.layout.fragment_ektp_home_profile,container,false)
        val userProfileData = EKTPHomeProfileViewModel().getUserData()

        binding.nameTextView.text = userProfileData[0]
        binding.phoneTextView.text = userProfileData[1]
        binding.emailTextView.text = userProfileData[2]

        return binding.root
    }


}