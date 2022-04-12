package com.elektra.ektp.ektpcreateaccount.view

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.elektra.ektp.R
import com.elektra.ektp.databinding.FragmentEktpCreateAccountSmsVerificationBinding
import com.elektra.ektp.ektpcreateaccount.viewmodel.EKTPCreateAccountActivityViewModel.Companion.uTel
import com.elektra.ektp.ektpcreateaccount.viewmodel.EKTPCreateAccountSMSVerificationViewModel
import com.elektra.ektp.ektputilies.uservalidations.UserValidations
import com.elektra.ektp.ektpsharedpreferences.EKTPUserApplication.Companion.preferences
import kotlinx.coroutines.Job

class EKTPCreateAccountSMSVerificationFragment : Fragment() {
    //Global variable for databinding
    private lateinit var binding: FragmentEktpCreateAccountSmsVerificationBinding
    private lateinit var loadingLayout: View
    //---
    //SharedPreferences variable access
    val validations = UserValidations()
    //---
    //ViewModel variable access
    private val smsVerificationViewModel: EKTPCreateAccountSMSVerificationViewModel by viewModels()
    //---
    //Global variables for user entries
    private lateinit var codeSMS: String
    private var codechar1 = ""
    private var codechar2 = ""
    private var codechar3 = ""
    private var codechar4 = ""
    private var codechar5 = ""
    //---
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        smsVerificationViewModel.resquestSMSTwiloCode(preferences.getPhoneUser())
        //Overriding OnBackPressed function to destroy fragment and activity
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
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_ektp_create_account_sms_verification, container, false)
        //Wrap this block code for all the lines with binding variable
        with(binding){
            smsContinueButton.isEnabled = false
            invalidSMSTextView.isVisible = false

            //TextWatcher function to listen for changes on editText
            verificationNumber1.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    codechar1 = s.toString()
                    codeSMS = validations.concatenaterCode(
                        codechar1, codechar2, codechar3, codechar4, codechar5
                    )
                    if (!codechar1.isNullOrBlank()) {
                        smsContinueButton.isEnabled = validations.codeLenghtChecker(codeSMS)
                        verificationNumber2.requestFocus()
                    }
                    else{
                        drawableSetter(true)
                        smsContinueButton.isEnabled = false
                    }
                }

                override fun afterTextChanged(s: Editable?) {}

            })
            //--

            //TextWatcher function to listen for changes on editText
            verificationNumber2.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    codechar2 = s.toString()
                    codeSMS = validations.concatenaterCode(
                        codechar1, codechar2, codechar3, codechar4, codechar5
                    )
                    if (!codechar2.isNullOrBlank()) {
                        smsContinueButton.isEnabled = validations.codeLenghtChecker(codeSMS)
                        verificationNumber3.requestFocus()
                    }
                    else{
                        drawableSetter(true)
                        smsContinueButton.isEnabled = false
                        verificationNumber1.requestFocus()
                    }
                }

                override fun afterTextChanged(s: Editable?) {}

            })
            //--

            //TextWatcher function to listen for changes on editText
            verificationNumber3.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    codechar3 = s.toString()
                    codeSMS = validations.concatenaterCode(
                        codechar1, codechar2, codechar3, codechar4, codechar5
                    )
                    if (!codechar3.isNullOrBlank()) {
                        smsContinueButton.isEnabled = validations.codeLenghtChecker(codeSMS)
                        verificationNumber4.requestFocus()
                    }
                    else{
                        drawableSetter(true)
                        smsContinueButton.isEnabled = false
                        verificationNumber2.requestFocus()
                    }
                }

                override fun afterTextChanged(s: Editable?) {}
            })
            //---

            //TextWatcher function to listen for changes on editText
            verificationNumber4.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    codechar4 = s.toString()
                    codeSMS = validations.concatenaterCode(
                        codechar1, codechar2, codechar3, codechar4, codechar5
                    )
                    if (!codechar4.isNullOrBlank() && codechar4.length == 1) {
                        smsContinueButton.isEnabled = validations.codeLenghtChecker(codeSMS)
                        verificationNumber5.requestFocus()
                    }
                    else{
                        drawableSetter(true)
                        smsContinueButton.isEnabled = false
                        verificationNumber3.requestFocus()
                    }
                }

                override fun afterTextChanged(s: Editable?) {}

            })
            //---

            //TextWatcher function to listen for changes on editText
            verificationNumber5.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    codechar5 = s.toString()
                    codeSMS = validations.concatenaterCode(
                        codechar1, codechar2, codechar3, codechar4, codechar5
                    )
                    if (!codechar5.isNullOrBlank()) {
                        smsContinueButton.isEnabled = validations.codeLenghtChecker(codeSMS)
                    }
                    else{
                        drawableSetter(true)
                        smsContinueButton.isEnabled = false
                        verificationNumber4.requestFocus()
                    }
                }

                override fun afterTextChanged(s: Editable?) {}

            })
            //---

            //TextWatcher function to listen for changes on editText
            smsContinueButton.setOnClickListener { view: View ->
                val value = smsVerificationViewModel.verifySMSTwiloCode(codeSMS, preferences.getPhoneUser())
                verifySMSresponse(value)
            }
            //---

            //onClickListener on appBar BackButton to destroy fragment and activity
            backAppbarButton.setOnClickListener { view : View ->
                findNavController().popBackStack()
            }
            //---

            resendCodeTextView.setOnClickListener {
                startCoolDown()
            }

            return  root
        }
    }

    fun getKeyVal(pressedKeyCode: Int):String{
        return when (pressedKeyCode){
            8 -> "1"
            9 -> "2"
            10 -> "3"
            11 -> "4"
            12 -> "5"
            13 -> "6"
            14 -> "7"
            15 -> "8"
            16 -> "9"
            else -> "0"
        }
    }
    private fun drawableSetter(isEnabled : Boolean){
        if (isEnabled){
            binding.verificationNumber1.setBackgroundResource(R.drawable.rounded_rectangle_gray)
            binding.verificationNumber2.setBackgroundResource(R.drawable.rounded_rectangle_gray)
            binding.verificationNumber3.setBackgroundResource(R.drawable.rounded_rectangle_gray)
            binding.verificationNumber4.setBackgroundResource(R.drawable.rounded_rectangle_gray)
            binding.verificationNumber5.setBackgroundResource(R.drawable.rounded_rectangle_gray)
        }else{
            binding.verificationNumber1.setBackgroundResource(R.drawable.validation_edit_text)
            binding.verificationNumber2.setBackgroundResource(R.drawable.validation_edit_text)
            binding.verificationNumber3.setBackgroundResource(R.drawable.validation_edit_text)
            binding.verificationNumber4.setBackgroundResource(R.drawable.validation_edit_text)
            binding.verificationNumber5.setBackgroundResource(R.drawable.validation_edit_text)
        }
    }

    private fun startCoolDown(){
        object : CountDownTimer(15000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                binding.resendCodeTextView.isEnabled = false
                binding.resendCodeTextView.text = getString(R.string.fragment_verification_resend_code) + " en " + (millisUntilFinished/1000).toString()
            }

            override fun onFinish() {
                smsVerificationViewModel.resquestSMSTwiloCode(preferences.getPhoneUser())
                binding.resendCodeTextView.isEnabled = true
                binding.resendCodeTextView.text = getString(R.string.fragment_verification_resend_code)
            }
        }.start()
    }

    private fun verifySMSresponse(value: Job) {
        loadingLayout = layoutInflater.inflate(R.layout.loading_alert_layout,null)
        val loadingAlert = alertDialogOpener(loadingLayout, requireContext())
        loadingAlert.show()
        loadingAlert.getWindow()?.setLayout(250, 250)
        var canContinue = false
        var attempts = 0
        val timer = object : CountDownTimer(7000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                if(value.isCompleted){
                    if (smsVerificationViewModel.canContinueñero){
                        loadingAlert.dismiss()
                        view?.findNavController()?.navigate(R.id.action_EKTPCreateAccountSMSVerificationFragment_to_EKPTCreateAccountRegisterFormFragment)
                        canContinue = true
                        cancel()
                    }else{
                        loadingAlert.dismiss()
                        drawableSetter(false)
                        canContinue = false
                        cancel()
                    }
                }
            }
            override fun onFinish() {
                if(value.isCompleted){
                    if (smsVerificationViewModel.canContinueñero){
                        loadingAlert.dismiss()
                        view?.findNavController()?.navigate(R.id.action_EKTPCreateAccountSMSVerificationFragment_to_EKPTCreateAccountRegisterFormFragment)
                        canContinue = true
                        cancel()
                    }else{
                        loadingAlert.dismiss()
                        drawableSetter(false)
                        canContinue = false
                        cancel()
                    }
                }else{
                    attempts = +1
                    if (attempts>2){
                        start()
                    }else{
                        loadingAlert.dismiss()
                        canContinue = false
                    }
                }
            }
        }
        timer.start()
    }

    private fun alertDialogOpener(dialogLayout: View, context: Context): AlertDialog {
        var alertDialog: AlertDialog? = null
        val alertDialogBuilder = AlertDialog.Builder(context)

        alertDialogBuilder.setView(dialogLayout)
        alertDialog = alertDialogBuilder.create()

        return alertDialog
    }
}