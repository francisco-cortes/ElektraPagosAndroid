package com.elektra.ektp.ektpreceivemoney.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.elektra.ektp.R
import com.elektra.ektp.databinding.FragmentEktpReceiveMtcnDetailsBinding

class EKTPReceiveMTCNDetailsFragment : Fragment() {
    private lateinit var binding: FragmentEktpReceiveMtcnDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  DataBindingUtil.inflate<FragmentEktpReceiveMtcnDetailsBinding>(inflater,R.layout.fragment_ektp_receive_mtcn_details, container, false)
        return binding.root
    }
}