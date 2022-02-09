package com.elektra.ektp.ektphome.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.elektra.ektp.R
import com.elektra.ektp.databinding.FragmentEktpHomeMainBinding
import com.elektra.ektp.ektphome.viewmodel.EKTPHomeMainViewModel

class EKTPHomeMainFragment : Fragment() {

    private lateinit var binding: FragmentEktpHomeMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentEktpHomeMainBinding>(inflater,R.layout.fragment_ektp_home_main,container,false)

        binding.userNameTextView.text = EKTPHomeMainViewModel().getUserHomeMain() + "!"

        return binding.root
    }
}