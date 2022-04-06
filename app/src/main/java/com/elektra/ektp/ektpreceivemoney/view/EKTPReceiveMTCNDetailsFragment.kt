package com.elektra.ektp.ektpreceivemoney.view

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.elektra.ektp.R
import com.elektra.ektp.databinding.FragmentEktpReceiveMtcnDetailsBinding

class EKTPReceiveMTCNDetailsFragment : Fragment() {
    private lateinit var binding: FragmentEktpReceiveMtcnDetailsBinding

    private lateinit var acceptButton: Button
    private lateinit var cantDespositAlertLayout: View
    private var mtcnString = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                view?.findNavController()?.navigate(R.id.action_EKTPReceiveMTCNDetailsFragment_to_EKTPReceiveMoneyMTCNFragment2)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val bundle = this.arguments
        mtcnString = bundle?.getString("mtcnString").toString()
        binding =  DataBindingUtil.inflate<FragmentEktpReceiveMtcnDetailsBinding>(inflater,R.layout.fragment_ektp_receive_mtcn_details, container, false)
        cantDespositAlertLayout = layoutInflater.inflate(R.layout.cant_deposit_alert_layout,null)
        var cantDepositAlertDialog: AlertDialog? = null
        val cantDepositDialogBuilder = AlertDialog.Builder(requireContext())

        cantDepositDialogBuilder.setView(cantDespositAlertLayout)
        acceptButton = cantDespositAlertLayout.findViewById(R.id.acceptButton)
        cantDepositAlertDialog = cantDepositDialogBuilder.create()

        acceptButton.setOnClickListener {
            cantDepositAlertDialog.dismiss()
        }
        //layout widgets
        with(binding){

            partnerMTCNTextView.text = mtcnString
            backAppbarButton.setOnClickListener {
                view?.findNavController()?.navigate(R.id.action_EKTPReceiveMTCNDetailsFragment_to_EKTPReceiveMoneyMTCNFragment2)
            }

            depositToButton.setOnClickListener {
                if ((0..1).random() == 2){//50% probabilities to make appear the case when there are no service
                    cantDepositAlertDialog.show()
                }else{
                    view?.findNavController()?.navigate(R.id.action_EKTPReceiveMTCNDetailsFragment_to_receiveMTCNAcountSuccesFragment, bundle)//navigate to the next fragment
                }
            }

            laterButton.setOnClickListener {
                activity?.finish()
                //view?.findNavController()?.navigate(R.id.action_EKTPReceiveMTCNDetailsFragment_to_EKTPReceiveMoneyMTCNFragment2)
            }
        }

        return binding.root
    }
}