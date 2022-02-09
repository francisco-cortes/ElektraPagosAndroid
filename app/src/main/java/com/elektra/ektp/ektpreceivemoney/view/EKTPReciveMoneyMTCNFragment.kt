package com.elektra.ektp.ektpreceivemoney.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.elektra.ektp.R
import com.elektra.ektp.databinding.FragmentEktpReciveMoneyMtcnBinding

class EKTPReciveMoneyMTCNFragment : Fragment() {

    private lateinit var binding: FragmentEktpReciveMoneyMtcnBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  DataBindingUtil.inflate<FragmentEktpReciveMoneyMtcnBinding>(inflater,R.layout.fragment_ektp_recive_money_mtcn, container, false)

        

        binding.root
    }

}