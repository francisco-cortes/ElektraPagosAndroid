package com.elektra.ektp.ektpreceivemoney.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.elektra.ektp.R
import com.elektra.ektp.databinding.FragmentReceiveMtcnAcountSuccesBinding

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
    ): View {
        // Inflate the layout for this fragment
        val bundle = this.arguments
        binding =  DataBindingUtil.inflate(inflater,R.layout.fragment_receive_mtcn_acount_succes, container, false)
        binding.doneButton.setOnClickListener {
            activity?.finish()
            //view?.findNavController()?.navigate(R.id.action_receiveMTCNAcountSuccesFragment_to_EKTPReceiveMoneyMTCNFragment)
        }

        binding.shareDetailsButton.setOnClickListener{
            view?.findNavController()?.navigate(R.id.action_receiveMTCNAcountSuccesFragment_to_EKTPReceiveMTCNShareImageFragment, bundle)
        }

        return binding.root
    }
}