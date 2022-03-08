package com.elektra.ektp.ektpreceivemoney.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.elektra.ektp.R
import com.elektra.ektp.databinding.FragmentEktpReceiveMtcnStatusBinding
import com.elektra.ektp.databinding.FragmentEktpReciveMoneyMtcnBinding

class EKTPReceiveMTCNStatusFragment : Fragment() {
    private lateinit var binding: FragmentEktpReceiveMtcnStatusBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()// used for back button behaviour to destroy the current fragment an return to previous fragment
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  DataBindingUtil.inflate<FragmentEktpReceiveMtcnStatusBinding>(inflater,R.layout.fragment_ektp_receive_mtcn_status, container, false)

        with(binding){
            binding.backAppbarButton.setOnClickListener {
                findNavController().popBackStack()
            }
            binding.seeDetailsButton.setOnClickListener {
                //view?.findNavController()?.navigate(R.id.action_EKTPReceiveMTCNStatusFragment_to_EKTPReceiveMTCNDetailsFragment)
            }
        }

        return binding.root
    }
}