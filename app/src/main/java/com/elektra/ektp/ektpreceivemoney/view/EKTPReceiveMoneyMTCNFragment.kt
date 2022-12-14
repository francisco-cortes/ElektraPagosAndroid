package com.elektra.ektp.ektpreceivemoney.view

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.elektra.ektp.R
import com.elektra.ektp.databinding.FragmentEktpReciveMoneyMtcnBinding

class EKTPReceiveMoneyMTCNFragment : Fragment() {
    private lateinit var binding: FragmentEktpReciveMoneyMtcnBinding
    private var toolTipShow = false
    private lateinit var mtcnDataIncorrectLayout: View
    private lateinit var mtcnNumIncorrectLayout: View
    private lateinit var mtcnTypeIncorrectLayout: View
    private lateinit var cantDeliverMtcnLayout: View
    private lateinit var cantOfferServiceLayout: View

    private lateinit var cantDeliverMtcnAcceptButton: Button
    private lateinit var cantOfferServiceAcceptButton: Button
    private lateinit var mtcnDataIncorrectAcceptButton: Button
    private lateinit var mtcnNumIncorrectAcceptButton: Button
    private lateinit var mtcnTypeIncorrectAcceptButton: Button

    private var bundle = Bundle()
    private var mtcnString = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Overriding obBackPressed to popBackStack fragment
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity?.finish()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment container, false
        binding =  DataBindingUtil.inflate(inflater,R.layout.fragment_ektp_recive_money_mtcn, container, false)

        mtcnDataIncorrectLayout = layoutInflater.inflate(R.layout.mtcn_data_incorrect_alert_layout,container, false)
        mtcnNumIncorrectLayout = layoutInflater.inflate(R.layout.mtcn_num_incorrect_alert_layout,container, false)
        mtcnTypeIncorrectLayout = layoutInflater.inflate(R.layout.mtcn_type_incorrect_alert_layout,container, false)
        cantDeliverMtcnLayout = layoutInflater.inflate(R.layout.cant_deliver_mtcn_alert_layout,container, false)
        cantOfferServiceLayout = layoutInflater.inflate(R.layout.cant_offer_service_alert_layout,container, false)

        mtcnDataIncorrectAcceptButton = mtcnDataIncorrectLayout.findViewById(R.id.acceptButton)
        mtcnNumIncorrectAcceptButton = mtcnNumIncorrectLayout.findViewById(R.id.acceptButton)
        mtcnTypeIncorrectAcceptButton = mtcnTypeIncorrectLayout.findViewById(R.id.acceptButton)
        cantDeliverMtcnAcceptButton = cantDeliverMtcnLayout.findViewById(R.id.acceptButton)
        cantOfferServiceAcceptButton = cantOfferServiceLayout.findViewById(R.id.acceptButton)

        val mtcnDataIncorrectAlert = alertDialogOpener(mtcnDataIncorrectLayout,requireContext())
        val mtcnTypeIncorrectAlert = alertDialogOpener(mtcnTypeIncorrectLayout,requireContext())
        val mtcnNumIncorrectAlert = alertDialogOpener(mtcnNumIncorrectLayout,requireContext())
        val cantDaliverMtcnAlertDialog = alertDialogOpener(cantDeliverMtcnLayout,requireContext())
        val cantOfferServiceAlertDialog = alertDialogOpener(cantOfferServiceLayout,requireContext())

        mtcnDataIncorrectAcceptButton.setOnClickListener {
            mtcnDataIncorrectAlert.dismiss()
        }
        mtcnNumIncorrectAcceptButton.setOnClickListener {
            mtcnNumIncorrectAlert.dismiss()
        }
        mtcnTypeIncorrectAcceptButton.setOnClickListener {
            mtcnTypeIncorrectAlert.dismiss()
        }

        cantDeliverMtcnAcceptButton.setOnClickListener {
            cantDaliverMtcnAlertDialog.dismiss()
        }

        cantOfferServiceAcceptButton.setOnClickListener {
            cantOfferServiceAlertDialog.dismiss()
        }

        with(binding){
            mtcnEntryEditText.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    mtcnString = s.toString()

                    when(checkMTCNKey(mtcnString)){
                        true -> {
                            binding.mtcnOk.isVisible = true
                            binding.consultButton.isEnabled = false
                            binding.mtcnEntryEditText.setBackgroundResource(R.drawable.validation_edit_text)
                        }
                        else ->{
                            binding.mtcnOk.isVisible = false
                            binding.consultButton.isEnabled = true
                            binding.mtcnEntryEditText.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                        }
                    }
                }
                override fun afterTextChanged(s: Editable?) {
                }
            })
            backAppbarButton.setOnClickListener {
                activity?.finish()
            }

            receiveInsertMTCNTextView.setOnClickListener {
                //change the status of hint tooltip
                if (toolTipShow){
                    toolTipShow=false
                    binding.toolTipImageView.isVisible = false
                    binding.toolTipTextView.isVisible = false
                }
                else{
                    toolTipShow=true
                    binding.toolTipImageView.isVisible =true
                    binding.toolTipTextView.isVisible = true
                }
                //----
            }
            consultButton.setOnClickListener { view:View ->
                bundle.putString("mtcnString", mtcnString)
                when(mtcnString){
                    "N291341385" -> mtcnDataIncorrectAlert.show()
                    "R57457734549" -> mtcnNumIncorrectAlert.show()
                    "A554011492295" -> mtcnTypeIncorrectAlert.show()
                    "X4591787456436" -> cantDaliverMtcnAlertDialog.show()
                    "E52455434563" -> cantOfferServiceAlertDialog.show()
                    else -> view.findNavController().navigate(R.id.action_EKTPReceiveMoneyMTCNFragment_to_EKTPReceiveMTCNDetailsFragment, bundle)
                }
            }
            backAppbarButton.setOnClickListener {
                activity?.finish()
            }
        }
        return binding.root
    }
    // MTCN Regex Checker
    private fun checkMTCNKey(keyToCheck: String): Boolean{
        if (keyToCheck.length in 8..16){
            if (keyToCheck.matches("^([Nn])\\d{9}".toRegex())){  //Intermex
                return false
            }
            if (keyToCheck.matches("^\\d{8,11}".toRegex())){    //WU, MG, Vigo, Barri,  BTS, OV
                return false
            }
            if (keyToCheck.matches("^([Rr])\\d{10,11}".toRegex())){ //Ria, Remitly
                return false
            }
            if (keyToCheck.matches("^([Aa])\\d{12}".toRegex())){ // Viamericas
                return false
            }
            if (keyToCheck.matches("^([Xx])\\d{13}".toRegex()) || keyToCheck.matches("^([Xx])\\d{15}".toRegex())){ //Dolex, Xoom
                return false
            }
            if (keyToCheck.matches("^([Ee])\\d{11}".toRegex())){ //Transnetwork
                return false
            }
        }
        return true
    }
//----
    private fun alertDialogOpener(dialogLayout: View, context: Context): AlertDialog {
    val alertDialog: AlertDialog?
    val alertDialogBuilder = AlertDialog.Builder(context)

        alertDialogBuilder.setView(dialogLayout)
        alertDialog = alertDialogBuilder.create()

        return alertDialog
    }
}