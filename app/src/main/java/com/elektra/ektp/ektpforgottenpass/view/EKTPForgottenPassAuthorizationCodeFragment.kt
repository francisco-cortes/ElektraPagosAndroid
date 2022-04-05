package com.elektra.ektp.ektpforgottenpass.view

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.elektra.ektp.R
import com.elektra.ektp.databinding.FragmentEktpForgottenPassAuthorizationCodeBinding
import com.elektra.ektp.ektpforgottenpass.view.viewmodel.EKTPForgottenPassAuthorizationCodeViewModel
import com.elektra.ektp.ektputilies.uservalidations.UserValidations

class EKTPForgottenPassAuthorizationCodeFragment : Fragment(){

    //Global databinding access variable
    private lateinit var binding: FragmentEktpForgottenPassAuthorizationCodeBinding
    //ViewModel access variable
    private val authorizationViewModel: EKTPForgottenPassAuthorizationCodeViewModel by viewModels()
    //UserValidations access variable
    val validations = UserValidations()

    //General data variables
    private lateinit var codeAuth: String
    private var codechar1 = ""
    private var codechar2 = ""
    private var codechar3 = ""
    private var codechar4 = ""
    private var codechar5 = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_ektp_forgotten_pass_authorization_code, container, false)

        //Wrap this block code for all the lines with binding variable
        with(binding){

            buttonAuth.isEnabled = false

            //TextWatcher function to listen for changes on editText
            verificationNumber1.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    codechar1 = s.toString()
                    codeAuth = validations.concatenaterCode(
                        codechar1, codechar2, codechar3, codechar4, codechar5
                    )
                    if (!codechar1.isNullOrBlank()) {
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
                    codechar2 = s.toString()
                    codeAuth = validations.concatenaterCode(
                        codechar1, codechar2, codechar3, codechar4, codechar5
                    )
                    if (!codechar2.isNullOrBlank()) {
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
                    codechar3 = s.toString()
                    codeAuth = validations.concatenaterCode(
                        codechar1, codechar2, codechar3, codechar4, codechar5
                    )
                    if (!codechar3.isNullOrBlank()) {
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
                    codechar4 = s.toString()
                    codeAuth = validations.concatenaterCode(
                        codechar1, codechar2, codechar3, codechar4, codechar5
                    )
                    if (!codechar4.isNullOrBlank()) {
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
                    codechar5 = s.toString()
                    codeAuth = validations.concatenaterCode(
                        codechar1, codechar2, codechar3, codechar4, codechar5
                    )
                    if (!codechar5.isNullOrBlank()) {
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

            //onClickListener on appBar BackButton to popBackStack fragment to earlier
            backAppbarButton.setOnClickListener { view : View ->
                findNavController().popBackStack()
            }
            //---

            /*verificationNumber1.setOnKeyListener( View.OnKeyListener { v, keyCode, event ->
                val pressedKey = event.keyCode
                Log.i("key pressed",pressedKey.toString())
                if (!codechar1.isNullOrBlank()&&verificationNumber1.isFocused&&pressedKey>=8&&pressedKey<=16) {
                    //Perform Code
                        verificationNumber2.requestFocus()
                        verificationNumber2.setText(getKeyVal(pressedKey))
                        verificationNumber2.setSelection(verificationNumber2.length())
                    return@OnKeyListener true
                }else{
                    false
                }
            })

            verificationNumber2.setOnKeyListener( View.OnKeyListener { v, keyCode, event ->
                val pressedKey = event.keyCode
                Log.i("key pressed",pressedKey.toString())
                if (!codechar2.isNullOrBlank()&&verificationNumber2.isFocused&&pressedKey>=8&&pressedKey<=16) {
                    //Perform Code
                    verificationNumber3.requestFocus()
                    verificationNumber3.setText(getKeyVal(pressedKey))
                    verificationNumber3.setSelection(verificationNumber3.length())
                    return@OnKeyListener true
                }else{
                    false
                }
            })

            verificationNumber3.setOnKeyListener( View.OnKeyListener { v, keyCode, event ->
                val pressedKey = event.keyCode
                Log.i("key pressed",pressedKey.toString())
                if (!codechar3.isNullOrBlank()&&verificationNumber3.isFocused&&pressedKey>=8&&pressedKey<=16) {
                    //Perform Code
                    verificationNumber4.requestFocus()
                    verificationNumber4.setText(getKeyVal(pressedKey))
                    verificationNumber4.setSelection(verificationNumber4.length())
                    return@OnKeyListener true
                }else{
                    false
                }
            })

            verificationNumber4.setOnKeyListener( View.OnKeyListener { v, keyCode, event ->
                val pressedKey = event.keyCode
                Log.i("key pressed",pressedKey.toString())
                if (!codechar4.isNullOrBlank()&&verificationNumber4.isFocused&&pressedKey>=8&&pressedKey<=16) {
                    //Perform Code
                    verificationNumber5.requestFocus()
                    verificationNumber5.setText(getKeyVal(pressedKey))
                    verificationNumber5.setSelection(verificationNumber5.length())
                    return@OnKeyListener true
                }else{
                    false
                }
            })*/

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
}