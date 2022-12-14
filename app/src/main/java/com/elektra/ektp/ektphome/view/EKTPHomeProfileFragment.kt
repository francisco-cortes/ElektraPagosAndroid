package com.elektra.ektp.ektphome.view

import android.content.Intent
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

    private var viewModel = EKTPHomeProfileViewModel() // instance for fragment view model

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_ektp_home_profile,container,false)
        val userProfileData = viewModel.getUserData()
        val userName = viewModel.getFullName()

        //layout controls
        with(binding){
            nameTextView.text = userName
            phoneTextView.text = userProfileData[0]
            emailTextView.text = userProfileData[1]

            contractLegalTextView.setOnClickListener {
                val intent = Intent(activity, EKTPHomeContractsActivity::class.java)
                activity?.startActivity(intent)
            }
        }
        //---
        return binding.root
    }


}