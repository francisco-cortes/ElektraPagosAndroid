package com.elektra.ektp.ektpcreateaccount.view

import android.app.Activity
import android.app.AlertDialog
import android.content.*
import android.os.Build
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
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.elektra.ektp.R
import com.elektra.ektp.databinding.FragmentEktpCreateAccountSmsVerificationBinding
import com.elektra.ektp.ektpcreateaccount.viewmodel.EKTPCreateAccountSMSVerificationViewModel
import com.elektra.ektp.ektputilies.uservalidations.UserValidations
import com.elektra.ektp.ektpsharedpreferences.EKTPUserApplication.Companion.preferences
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.common.api.Status
import kotlinx.coroutines.Job
import java.util.regex.Pattern

@Suppress("DEPRECATION")
class EKTPCreateAccountSMSVerificationFragment : Fragment() {

    private val smsConsentRequest = 2  // Set to an unused request code
    private val smsVerificationReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (SmsRetriever.SMS_RETRIEVED_ACTION == intent.action) {
                val extras = intent.extras
                val smsRetrieverStatus = extras?.get(SmsRetriever.EXTRA_STATUS) as Status

                when (smsRetrieverStatus.statusCode) {
                    CommonStatusCodes.SUCCESS -> {
                        // Get consent intent
                        val consentIntent = extras.getParcelable<Intent>(SmsRetriever.EXTRA_CONSENT_INTENT)
                        try {
                            // Start activity to show consent dialog to user, activity must be started in
                            // 5 minutes, otherwise you'll receive another TIMEOUT intent
                            startActivityForResult(consentIntent, smsConsentRequest)
                        } catch (e: ActivityNotFoundException) {
                            // Handle the exception ...
                        }
                    }
                    CommonStatusCodes.TIMEOUT -> {
                        // Time out occurred, handle the error.
                    }
                }
            }
        }
    }

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
    private var codeChar1 = ""
    private var codeChar2 = ""
    private var codeChar3 = ""
    private var codeChar4 = ""
    private var codeChar5 = ""
    //---
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //smsVerificationViewModel.resquestSMSTwiloCode()
        //Overriding OnBackPressed function to destroy fragment and activity
        startSmartUserConsent()
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        })
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_ektp_create_account_sms_verification, container, false)
        loadingLayout = layoutInflater.inflate(R.layout.loading_alert_layout,container,false)

        val intentFilter = IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION)
        requireActivity().registerReceiver(smsVerificationReceiver,intentFilter)

        //Wrap this block code for all the lines with binding variable
        with(binding){
            smsContinueButton.isEnabled = false
            invalidSMSTextView.isVisible = false

            //TextWatcher function to listen for changes on editText
            verificationNumber1.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    codeChar1 = s.toString()
                    codeSMS = validations.concatenaterCode(
                        codeChar1, codeChar2, codeChar3, codeChar4, codeChar5
                    )
                    if (codeChar1.isNotBlank()) {
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
                    codeChar2 = s.toString()
                    codeSMS = validations.concatenaterCode(
                        codeChar1, codeChar2, codeChar3, codeChar4, codeChar5
                    )
                    if (codeChar2.isNotBlank()) {
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
                    codeChar3 = s.toString()
                    codeSMS = validations.concatenaterCode(
                        codeChar1, codeChar2, codeChar3, codeChar4, codeChar5
                    )
                    if (codeChar3.isNotBlank()) {
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
                    codeChar4 = s.toString()
                    codeSMS = validations.concatenaterCode(
                        codeChar1, codeChar2, codeChar3, codeChar4, codeChar5
                    )
                    if (codeChar4.isNotBlank()) {
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
                    codeChar5 = s.toString()
                    codeSMS = validations.concatenaterCode(
                        codeChar1, codeChar2, codeChar3, codeChar4, codeChar5
                    )
                    if (codeChar5.isNotBlank()) {
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
            smsContinueButton.setOnClickListener {
                //val value = smsVerificationViewModel.verifySMSTwilioCode(codeSMS)
                //verifySMResponse(value)
                requireActivity().unregisterReceiver(smsVerificationReceiver)
                when(preferences.getLocalStatus()){
                    "preResgistrado" ->fragmentReplacer(EKTPCreateAccountRegisterFormFragment())
                    "Registrado" -> fragmentReplacer(EKTPCreateAccountCreatePassFragment())
                    "clienteActivado" -> fragmentReplacer(EKTPCreateAccountSuccessfulFragment())
                    "clienteBancarizado" -> activity?.finish()
                    else -> fragmentReplacer(EKTPCreateAccountRegisterFormFragment())
                }
            }
            //---

            //onClickListener on appBar BackButton to destroy fragment and activity
            backAppbarButton.setOnClickListener {
                findNavController().popBackStack()
            }
            //---
            verificationNumber1.setOnKeyListener( View.OnKeyListener { _, _, event ->
                val pressedKey = event.keyCode
                Log.i("key pressed",pressedKey.toString())
                if (codeChar1.isNotBlank() &&verificationNumber1.isFocused&&pressedKey>=8&&pressedKey<=16) {
                    //Perform Code
                    verificationNumber2.requestFocus()
                    verificationNumber2.setText(getKeyVal(pressedKey))
                    verificationNumber2.setSelection(verificationNumber2.length())
                    return@OnKeyListener true
                }else{
                    false
                }
            })

            verificationNumber2.setOnKeyListener( View.OnKeyListener { _, _, event ->
                val pressedKey = event.keyCode
                Log.i("key pressed",pressedKey.toString())
                if (codeChar2.isNotBlank() &&verificationNumber2.isFocused&&pressedKey>=8&&pressedKey<=16) {
                    //Perform Code
                    verificationNumber3.requestFocus()
                    verificationNumber3.setText(getKeyVal(pressedKey))
                    verificationNumber3.setSelection(verificationNumber3.length())
                    return@OnKeyListener true
                }else{
                    false
                }
            })

            verificationNumber3.setOnKeyListener( View.OnKeyListener { _, _, event ->
                val pressedKey = event.keyCode
                Log.i("key pressed",pressedKey.toString())
                if (codeChar3.isNotBlank() &&verificationNumber3.isFocused&&pressedKey>=8&&pressedKey<=16) {
                    //Perform Code
                    verificationNumber4.requestFocus()
                    verificationNumber4.setText(getKeyVal(pressedKey))
                    verificationNumber4.setSelection(verificationNumber4.length())
                    return@OnKeyListener true
                }else{
                    false
                }
            })

            verificationNumber4.setOnKeyListener( View.OnKeyListener { _, _, event ->
                val pressedKey = event.keyCode
                Log.i("key pressed",pressedKey.toString())
                if (codeChar4.isNotBlank() &&verificationNumber4.isFocused&&pressedKey>=8&&pressedKey<=16) {
                    //Perform Code
                    verificationNumber5.requestFocus()
                    verificationNumber5.setText(getKeyVal(pressedKey))
                    verificationNumber5.setSelection(verificationNumber5.length())
                    return@OnKeyListener true
                }else{
                    false
                }
            })

            resendCodeTextView.setOnClickListener {
                smsVerificationViewModel.resquestSMSTwiloCode()
                startCoolDown()
            }

            return  root
        }
    }

    private fun getKeyVal(pressedKeyCode: Int):String{
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
            binding.invalidSMSTextView.isVisible = false
        }else{
            binding.verificationNumber1.setBackgroundResource(R.drawable.validation_edit_text)
            binding.verificationNumber2.setBackgroundResource(R.drawable.validation_edit_text)
            binding.verificationNumber3.setBackgroundResource(R.drawable.validation_edit_text)
            binding.verificationNumber4.setBackgroundResource(R.drawable.validation_edit_text)
            binding.verificationNumber5.setBackgroundResource(R.drawable.validation_edit_text)
            binding.invalidSMSTextView.isVisible = true
        }
    }

    private fun startCoolDown(){
        object : CountDownTimer(15000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                binding.resendCodeTextView.isEnabled = false
                binding.resendCodeSecondsTextView.isVisible = true
                binding.resendCodeSecondsTextView.text = (millisUntilFinished/1000).toString()
            }

            override fun onFinish() {
                binding.resendCodeTextView.isEnabled = true
                binding.resendCodeSecondsTextView.isVisible = false
            }
        }.start()
    }

    private fun verifySMSresponse(value: Job) {
        val loadingAlert = alertDialogOpener(loadingLayout, requireContext())
        loadingAlert.show()
        loadingAlert.window?.setLayout(250, 250)
        var attempts: Int
        val timer = object : CountDownTimer(7000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                if(value.isCompleted){
                    if (smsVerificationViewModel.canContinue){
                        loadingAlert.dismiss()
                        when(preferences.getLocalStatus()){
                            "preResgistrado" ->fragmentReplacer(EKTPCreateAccountRegisterFormFragment())
                            "Registrado" -> fragmentReplacer(EKTPCreateAccountCreatePassFragment())
                            "clienteActivado" -> fragmentReplacer(EKTPCreateAccountSuccessfulFragment())
                            "clienteBancarizado" -> activity?.finish()
                            else -> fragmentReplacer(EKTPCreateAccountRegisterFormFragment())
                        }
                        //view?.findNavController()?.navigate(R.id.action_EKTPCreateAccountSMSVerificationFragment_to_EKPTCreateAccountRegisterFormFragment)
                        cancel()
                    }else{
                        loadingAlert.dismiss()
                        drawableSetter(false)
                        cancel()
                    }
                }
            }
            override fun onFinish() {
                if(value.isCompleted){
                    if (smsVerificationViewModel.canContinue){
                        loadingAlert.dismiss()
                        when(preferences.getLocalStatus()){
                            "preResgistrado" ->fragmentReplacer(EKTPCreateAccountRegisterFormFragment())
                            "Registrado" -> fragmentReplacer(EKTPCreateAccountCreatePassFragment())
                            "clienteActivado" -> fragmentReplacer(EKTPCreateAccountSuccessfulFragment())
                            "clienteBancarizado" -> activity?.finish()
                            else -> fragmentReplacer(EKTPCreateAccountRegisterFormFragment())
                        }
                        //view?.findNavController()?.navigate(R.id.action_EKTPCreateAccountSMSVerificationFragment_to_EKPTCreateAccountRegisterFormFragment)
                        cancel()
                    }else{
                        loadingAlert.dismiss()
                        drawableSetter(false)
                        cancel()
                    }
                }else{
                    attempts = +1
                    if (attempts>2){
                        start()
                    }else{
                        loadingAlert.dismiss()
                    }
                }
            }
        }
        timer.start()
    }

    private fun alertDialogOpener(dialogLayout: View, context: Context): AlertDialog {
        val alertDialog: AlertDialog?
        val alertDialogBuilder = AlertDialog.Builder(context)

        alertDialogBuilder.setView(dialogLayout)
        alertDialog = alertDialogBuilder.create()

        return alertDialog
    }
    private fun fragmentReplacer(fragment: Fragment){
        requireActivity()
            .supportFragmentManager
            .beginTransaction()
            .replace(R.id.CreateAccountNavigatorHost,fragment)
            .commitNow()//open the biometric login fragment
    }

    private fun startSmartUserConsent(){
        SmsRetriever.getClient(requireActivity()).startSmsUserConsent(null)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            // ...
            smsConsentRequest ->
                // Obtain the phone number from the result
                if (resultCode == Activity.RESULT_OK && data != null) {
                    // Get SMS message content
                    val message = data.getStringExtra(SmsRetriever.EXTRA_SMS_MESSAGE)
                    // Extract one-time code from the message and complete verification
                    // `message` contains the entire text of the SMS message, so you will need
                    // to parse the string.
                    getOtP(message) // define this function

                    // send one time code to the server
                }
        }
    }
    private fun getOtP(message: String?){
        val otpPattern = Pattern.compile("(|^)\\d{5}")
        val matcher = otpPattern.matcher(message!!)
        if (matcher.find()){
            val code = matcher.group(0)
            binding.verificationNumber1.setText(code?.get(0).toString())
            binding.verificationNumber2.setText(code?.get(1).toString())
            binding.verificationNumber3.setText(code?.get(2).toString())
            binding.verificationNumber4.setText(code?.get(3).toString())
            binding.verificationNumber5.setText(code?.get(4).toString())
        }
    }
}