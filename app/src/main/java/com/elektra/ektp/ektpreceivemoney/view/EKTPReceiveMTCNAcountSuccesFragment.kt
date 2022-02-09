package com.elektra.ektp.ektpreceivemoney.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.elektra.ektp.R
import com.elektra.ektp.databinding.FragmentReceiveMtcnAcountSuccesBinding

class EKTPReceiveMTCNAcountSuccesFragment : Fragment() {

    private lateinit var binding: FragmentReceiveMtcnAcountSuccesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  DataBindingUtil.inflate<FragmentReceiveMtcnAcountSuccesBinding>(inflater,R.layout.fragment_receive_mtcn_acount_succes, container, false)
        binding.doneButton.setOnClickListener {
            activity?.finish()
        }
        return binding.root
    }
}