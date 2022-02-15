package com.elektra.ektp.ektphome.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.elektra.ektp.R
import com.elektra.ektp.databinding.FragmentEktpHomeNoticeBinding
import com.elektra.ektp.ektphome.viewmodel.EKTPHomeNoticeViewModel

class EKTPHomeNoticeFragment : Fragment() {

    private lateinit var binding : FragmentEktpHomeNoticeBinding

    private var viewModel = EKTPHomeNoticeViewModel() //instance view model for home notice fragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentEktpHomeNoticeBinding>(inflater,R.layout.fragment_ektp_home_notice, container, false)
        return binding.root
    }

}