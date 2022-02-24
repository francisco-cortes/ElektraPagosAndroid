package com.elektra.ektp.ektpreceivemoney.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.content.FileProvider
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.elektra.ektp.R
import com.elektra.ektp.databinding.FragmentReceiveMtcnAcountSuccesBinding
import java.io.File
import java.io.FileOutputStream

class EKTPReceiveMTCNAcountSuccesFragment : Fragment() {

    private lateinit var binding: FragmentReceiveMtcnAcountSuccesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Overriding obBackPressed to popBackStack fragment
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                view?.findNavController()?.navigate(R.id.action_receiveMTCNAcountSuccesFragment_to_EKTPReceiveMoneyMTCNFragment)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  DataBindingUtil.inflate<FragmentReceiveMtcnAcountSuccesBinding>(inflater,R.layout.fragment_receive_mtcn_acount_succes, container, false)
        binding.doneButton.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_receiveMTCNAcountSuccesFragment_to_EKTPReceiveMoneyMTCNFragment)
        }

        binding.shareDetailsButton.setOnClickListener{
            view?.findNavController()?.navigate(R.id.action_receiveMTCNAcountSuccesFragment_to_EKTPReceiveMTCNShareImageFragment)
        }

        return binding.root
    }
}