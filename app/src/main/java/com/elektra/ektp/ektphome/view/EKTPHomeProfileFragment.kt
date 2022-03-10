package com.elektra.ektp.ektphome.view

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils.replace
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

    private var viewModel = EKTPHomeProfileViewModel() // instance for fragment view model

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentEktpHomeProfileBinding>(inflater,R.layout.fragment_ektp_home_profile,container,false)
        val userProfileData = viewModel.getUserData()

        //layout controls
        with(binding){
            nameTextView.text = userProfileData[0] +" "+ userProfileData[1] +" "+ userProfileData[2]
            phoneTextView.text = userProfileData[3]
            emailTextView.text = userProfileData[4]

            contractLegalTextView.setOnClickListener {
                val intent = Intent(activity, EKTPHomeContractsActivity::class.java)
                activity?.startActivity(intent)
            }
        }
        //---
        return binding.root
    }


}