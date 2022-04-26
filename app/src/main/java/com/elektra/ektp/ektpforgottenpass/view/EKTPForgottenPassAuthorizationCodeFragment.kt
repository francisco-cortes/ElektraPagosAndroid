package com.elektra.ektp.ektpforgottenpass.view

import android.app.Activity
import android.content.*
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.elektra.ektp.R
import com.elektra.ektp.databinding.FragmentEktpForgottenPassAuthorizationCodeBinding
import com.elektra.ektp.ektpforgottenpass.view.viewmodel.EKTPForgottenPassAuthorizationCodeViewModel
import com.elektra.ektp.ektputilies.smsreader.EKTPSMSBrodcastReciver
import com.elektra.ektp.ektputilies.uservalidations.UserValidations
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.common.api.Status
import java.util.regex.Pattern


@Suppress("DEPRECATION")
class EKTPForgottenPassAuthorizationCodeFragment : Fragment(){
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

    //Global databinding access variable
    private lateinit var binding: FragmentEktpForgottenPassAuthorizationCodeBinding
    //ViewModel access variable
    private val authorizationViewModel: EKTPForgottenPassAuthorizationCodeViewModel by viewModels()

    //UserValidations access variable
    val validations = UserValidations()

    //General data variables
    private lateinit var codeAuth: String
    private var codeChar1 = ""
    private var codeChar2 = ""
    private var codeChar3 = ""
    private var codeChar4 = ""
    private var codeChar5 = ""
    private var smsReceiver: EKTPSMSBrodcastReciver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startSmartUserConsent()
        //Overriding OnBackPressed function to destroy fragment and activity
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                    findNavController().popBackStack()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_ektp_forgotten_pass_authorization_code, container, false)
        val intentFilter = IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION)
        requireActivity().registerReceiver(smsVerificationReceiver,intentFilter)
        //Wrap this block code for all the lines with binding variable
        with(binding){

            buttonAuth.isEnabled = false

            //TextWatcher function to listen for changes on editText
            verificationNumber1.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    codeChar1 = s.toString()
                    codeAuth = validations.concatenaterCode(
                        codeChar1, codeChar2, codeChar3, codeChar4, codeChar5
                    )
                    if (codeChar1.isNotBlank()) {
                        buttonAuth.isEnabled = validations.codeLenghtChecker(codeAuth)
                        verificationNumber2.requestFocus()
                    }
                    else{
                        drawableSetter(true)
                        buttonAuth.isEnabled = false
                    }
                }
                override fun afterTextChanged(s: Editable?) {
                }
            })
            //---

            //TextWatcher function to listen for changes on editText
            verificationNumber2.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    codeChar2 = s.toString()
                    codeAuth = validations.concatenaterCode(
                        codeChar1, codeChar2, codeChar3, codeChar4, codeChar5
                    )
                    if (codeChar2.isNotBlank()) {
                        buttonAuth.isEnabled = validations.codeLenghtChecker(codeAuth)
                        verificationNumber3.requestFocus()
                    }
                    else{
                        drawableSetter(true)
                        buttonAuth.isEnabled = false
                        verificationNumber1.requestFocus()
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                }

            })
            //---

            //TextWatcher function to listen for changes on editText
            verificationNumber3.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    codeChar3 = s.toString()
                    codeAuth = validations.concatenaterCode(
                        codeChar1, codeChar2, codeChar3, codeChar4, codeChar5
                    )
                    if (codeChar3.isNotBlank()) {
                        buttonAuth.isEnabled = validations.codeLenghtChecker(codeAuth)
                        verificationNumber4.requestFocus()
                    }
                    else{
                        drawableSetter(true)
                        buttonAuth.isEnabled = false
                        verificationNumber2.requestFocus()
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                }

            })
            //---

            //TextWatcher function to listen for changes on editText
            verificationNumber4.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    codeChar4 = s.toString()
                    codeAuth = validations.concatenaterCode(
                        codeChar1, codeChar2, codeChar3, codeChar4, codeChar5
                    )
                    if (codeChar4.isNotBlank()) {
                        buttonAuth.isEnabled = validations.codeLenghtChecker(codeAuth)
                        verificationNumber5.requestFocus()
                    }
                    else{
                        drawableSetter(true)
                        buttonAuth.isEnabled = false
                        verificationNumber3.requestFocus()
                    }
                }

                override fun afterTextChanged(s: Editable?) {}
            })
            //---

            //TextWatcher function to listen for changes on editText
            verificationNumber5.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    codeChar5 = s.toString()
                    codeAuth = validations.concatenaterCode(
                        codeChar1, codeChar2, codeChar3, codeChar4, codeChar5
                    )
                    if (codeChar5.isNotBlank()) {
                        buttonAuth.isEnabled = validations.codeLenghtChecker(codeAuth)
                    }
                    else{
                        drawableSetter(true)
                        buttonAuth.isEnabled = false
                        verificationNumber4.requestFocus()
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                }

            })
            //---

            //onClickListener function to listen for  ask for check authorization code
            buttonAuth.setOnClickListener { view: View ->
                if (!authorizationViewModel.checkCodeAuthentication(codeAuth)){
                    view.findNavController().navigate(R.id.action_EKTPForgottenPassAuthorizationCodeFragment_to_EKTPForgottenPassNewPassFragment)
                }
                else{
                    drawableSetter(false)
                    invalidCodeTextView.isVisible = true
                }
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

            //onClickListener on appBar BackButton to popBackStack fragment to earlier
            backAppbarButton.setOnClickListener {
                findNavController().popBackStack()
            }
            //---
            return root
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
        }else{
            binding.verificationNumber1.setBackgroundResource(R.drawable.validation_edit_text)
            binding.verificationNumber2.setBackgroundResource(R.drawable.validation_edit_text)
            binding.verificationNumber3.setBackgroundResource(R.drawable.validation_edit_text)
            binding.verificationNumber4.setBackgroundResource(R.drawable.validation_edit_text)
            binding.verificationNumber5.setBackgroundResource(R.drawable.validation_edit_text)
        }
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
                } else {
                    // Consent denied. User can type OTC manually.
                }
        }
    }
    private fun getOtP(message: String?){
        val otpPattern = Pattern.compile("(|^)\\d{5}")
        val matcher = otpPattern.matcher(message.toString())
        if (matcher.find()){
            val code = matcher.group(0)
            binding.verificationNumber1.setText(code?.get(0).toString())
            binding.verificationNumber2.setText(code?.get(1).toString())
            binding.verificationNumber3.setText(code?.get(2).toString())
            binding.verificationNumber4.setText(code?.get(3).toString())
            binding.verificationNumber5.setText(code?.get(4).toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        smsReceiver = null
    }

}