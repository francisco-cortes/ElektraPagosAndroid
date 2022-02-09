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
import com.elektra.ektp.databinding.FragmentEktpReceiveMtcnDetailsBinding

class EKTPReceiveMTCNDetailsFragment : Fragment() {
    private lateinit var binding: FragmentEktpReceiveMtcnDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  DataBindingUtil.inflate<FragmentEktpReceiveMtcnDetailsBinding>(inflater,R.layout.fragment_ektp_receive_mtcn_details, container, false)

        binding.backAppbarButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.depositToButton.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_EKTPReceiveMTCNDetailsFragment_to_receiveMTCNAcountSuccesFragment)
        }

        binding.laterButton.setOnClickListener {
            activity?.finish()
        }

        return binding.root
    }
}