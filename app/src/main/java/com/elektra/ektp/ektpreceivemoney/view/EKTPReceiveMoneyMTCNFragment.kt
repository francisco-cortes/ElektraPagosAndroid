package com.elektra.ektp.ektpreceivemoney.view

import android.app.AlertDialog
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
import androidx.navigation.fragment.findNavController
import com.elektra.ektp.R
import com.elektra.ektp.databinding.FragmentEktpReciveMoneyMtcnBinding
import com.elektra.ektp.ektphome.view.EKTPHomeActivity
import com.elektra.ektp.ektpreceivemoney.viewmodel.EKTPReceiveMoneyMTCNViewModel

class EKTPReceiveMoneyMTCNFragment : Fragment() {
    private lateinit var binding: FragmentEktpReciveMoneyMtcnBinding
    private var toolTipShow = false

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
    ): View? {
        // Inflate the layout for this fragment
        binding =  DataBindingUtil.inflate<FragmentEktpReciveMoneyMtcnBinding>(inflater,R.layout.fragment_ektp_recive_money_mtcn, container, false)

        with(binding){
            mtcnEntryEditText.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val enableCheck = checkMTCNKey(s.toString()) //check if input text

                    when(enableCheck){
                        true -> {
                            //binding.mtcnOk.isVisible = true
                            binding.consultButton.isEnabled = false
                            //binding.mtcnEntryEditText.setBackgroundResource(R.drawable.validation_edit_text)
                        }
                        else ->{
                            //binding.mtcnOk.isVisible = false
                            binding.consultButton.isEnabled = true
                            //binding.mtcnEntryEditText.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                        }
                    }
                }
                override fun afterTextChanged(s: Editable?) {
                }
            })
            backAppbarButton.setOnClickListener { view: View ->
                activity?.finish()
            }
            mtcnHintPopUpButton.setOnClickListener { view: View ->
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
                view.findNavController().navigate(R.id.action_EKTPReceiveMoneyMTCNFragment_to_EKTPReceiveMTCNDetailsFragment)
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
            if (keyToCheck.matches("^(N|n)\\d{9}".toRegex())){  //Intermex
                return false
            }
            if (keyToCheck.matches("^\\d{8,11}".toRegex())){    //WU, MG, Vigo, Barri,  BTS, OV
                return false
            }
            if (keyToCheck.matches("^(R|r)\\d{10,11}".toRegex())){ //Ria, Remitly
                return false
            }
            if (keyToCheck.matches("^(A|a)\\d{12}".toRegex())){ // Viamericas
                return false
            }
            if (keyToCheck.matches("^(X|x)\\d{13}".toRegex()) || keyToCheck.matches("^(X|x)\\d{15}".toRegex())){ //Dolex, Xoom
                return false
            }
            if (keyToCheck.matches("^(E|e)\\d{11}".toRegex())){ //Transnetwork
                return false
            }
        }
        return true
    }
//----
}