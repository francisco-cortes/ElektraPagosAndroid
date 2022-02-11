package com.elektra.ektp.ektpforgottenpass.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import com.elektra.ektp.databinding.FragmentEktpForgottenPassAuthorizationCodeBinding
import com.elektra.ektp.ektpforgottenpass.view.viewmodel.EKTPForgottenPassAuthorizationCodeViewModel
import com.elektra.ektp.ektptoaster.EKTPToaster
import com.elektra.ektp.uservalidations.UserValidations

class EKTPForgottenPassAuthorizationCodeFragment : Fragment(){

    //Global databinding access variable
    private lateinit var binding: FragmentEktpForgottenPassAuthorizationCodeBinding
    //ViewModel access variable
    private val authorizationViewModel: EKTPForgottenPassAuthorizationCodeViewModel by viewModels()
    //UserValidations access variable
    val validations = UserValidations()

    //General data variables
    private lateinit var codeSMS: String
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
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
        R.layout.fragment_ektp_forgotten_pass_authorization_code, container, false)

        //Wrap this block code for all the lines with binding variable
        with(binding){
            buttonAuth.isEnabled = false

            //TextWatcher function to listen for changes on editText
            verificationNumber1.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    codechar1 = s.toString()
                    verificationNumber1.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    verificationNumber2.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    verificationNumber3.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    verificationNumber4.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    verificationNumber5.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    invalidCodeTextView.isVisible = false
                    codeSMS = validations.concatenaterCode(
                        codechar1, codechar2, codechar3, codechar4, codechar5
                    )
                    if (!codechar1.isNullOrBlank() && codechar1.length == 1) {
                        buttonAuth.isEnabled = validations.codeLenghtChecker(codeSMS)
                        verificationNumber2.requestFocus()
                    }
                    else{
                        buttonAuth.isEnabled = false
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    codechar1 = s.toString()
                    verificationNumber1.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    verificationNumber2.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    verificationNumber3.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    verificationNumber4.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    verificationNumber5.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    invalidCodeTextView.isVisible = false
                    codeSMS = validations.concatenaterCode(
                        codechar1, codechar2, codechar3, codechar4, codechar5
                    )
                    if (!codechar1.isNullOrBlank() && codechar1.length == 1) {
                        buttonAuth.isEnabled = validations.codeLenghtChecker(codeSMS)
                        verificationNumber2.requestFocus()
                    }
                    else{
                        buttonAuth.isEnabled = validations.codeLenghtChecker(codeSMS)
                    }
                }

            })
            //---

            //TextWatcher function to listen for changes on editText
            verificationNumber2.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    codechar2 = s.toString()
                    verificationNumber1.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    verificationNumber2.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    verificationNumber3.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    verificationNumber4.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    verificationNumber5.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    invalidCodeTextView.isVisible = false
                    codeSMS = validations.concatenaterCode(
                        codechar1, codechar2, codechar3, codechar4, codechar5
                    )
                    if (!codechar2.isNullOrBlank() && codechar2.length == 1) {
                        buttonAuth.isEnabled = validations.codeLenghtChecker(codeSMS)
                        verificationNumber3.requestFocus()
                    }
                    else{
                        buttonAuth.isEnabled = false
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    codechar2 = s.toString()
                    verificationNumber1.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    verificationNumber2.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    verificationNumber3.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    verificationNumber4.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    verificationNumber5.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    invalidCodeTextView.isVisible = false
                    codeSMS = validations.concatenaterCode(
                        codechar1, codechar2, codechar3, codechar4, codechar5
                    )
                    if (!codechar2.isNullOrBlank() && codechar2.length == 1) {
                        buttonAuth.isEnabled = validations.codeLenghtChecker(codeSMS)
                        verificationNumber3.requestFocus()
                    }
                    else{
                        buttonAuth.isEnabled = validations.codeLenghtChecker(codeSMS)
                    }
                }

            })
            //---

            //TextWatcher function to listen for changes on editText
            verificationNumber3.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    codechar3 = s.toString()
                    verificationNumber1.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    verificationNumber2.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    verificationNumber3.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    verificationNumber4.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    verificationNumber5.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    invalidCodeTextView.isVisible = false
                    codeSMS = validations.concatenaterCode(
                        codechar1, codechar2, codechar3, codechar4, codechar5
                    )
                    if (!codechar3.isNullOrBlank() && codechar3.length == 1) {
                        buttonAuth.isEnabled = validations.codeLenghtChecker(codeSMS)
                        verificationNumber4.requestFocus()
                    }
                    else{
                        buttonAuth.isEnabled = false
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    codechar3 = s.toString()
                    verificationNumber1.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    verificationNumber2.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    verificationNumber3.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    verificationNumber4.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    verificationNumber5.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    invalidCodeTextView.isVisible = false
                    codeSMS = validations.concatenaterCode(
                        codechar1, codechar2, codechar3, codechar4, codechar5
                    )
                    if (!codechar3.isNullOrBlank() && codechar3.length == 1) {
                        buttonAuth.isEnabled = validations.codeLenghtChecker(codeSMS)
                        verificationNumber4.requestFocus()
                    }
                    else{
                        buttonAuth.isEnabled = validations.codeLenghtChecker(codeSMS)
                    }
                }

            })
            //---

            //TextWatcher function to listen for changes on editText
            verificationNumber4.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    codechar4 = s.toString()
                    verificationNumber1.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    verificationNumber2.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    verificationNumber3.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    verificationNumber4.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    verificationNumber5.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    invalidCodeTextView.isVisible = false
                    codeSMS = validations.concatenaterCode(
                        codechar1, codechar2, codechar3, codechar4, codechar5
                    )
                    if (!codechar4.isNullOrBlank() && codechar4.length == 1) {
                        buttonAuth.isEnabled = validations.codeLenghtChecker(codeSMS)
                        verificationNumber5.requestFocus()
                    }
                    else{
                        buttonAuth.isEnabled = false
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    codechar4 = s.toString()
                    verificationNumber1.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    verificationNumber2.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    verificationNumber3.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    verificationNumber4.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    verificationNumber5.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    invalidCodeTextView.isVisible = false
                    codeSMS = validations.concatenaterCode(
                        codechar1, codechar2, codechar3, codechar4, codechar5
                    )
                    if (!codechar4.isNullOrBlank() && codechar4.length == 1) {
                        buttonAuth.isEnabled = validations.codeLenghtChecker(codeSMS)
                        verificationNumber5.requestFocus()
                    }
                    else{
                        buttonAuth.isEnabled = validations.codeLenghtChecker(codeSMS)
                    }
                }

            })
            //---

            //TextWatcher function to listen for changes on editText
            verificationNumber5.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    codechar5 = s.toString()
                    verificationNumber1.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    verificationNumber2.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    verificationNumber3.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    verificationNumber4.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    verificationNumber5.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    invalidCodeTextView.isVisible = false
                    codeSMS = validations.concatenaterCode(
                        codechar1, codechar2, codechar3, codechar4, codechar5
                    )
                    if (!codechar5.isNullOrBlank() && codechar5.length == 1) {
                        buttonAuth.isEnabled = validations.codeLenghtChecker(codeSMS)
                    }
                    else{
                        buttonAuth.isEnabled = false
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    codechar5 = s.toString()
                    verificationNumber1.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    verificationNumber2.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    verificationNumber3.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    verificationNumber4.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    verificationNumber5.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    invalidCodeTextView.isVisible = false
                    codeSMS = validations.concatenaterCode(
                        codechar1, codechar2, codechar3, codechar4, codechar5
                    )
                    if (!codechar5.isNullOrBlank() && codechar5.length == 1) {
                        buttonAuth.isEnabled = validations.codeLenghtChecker(codeSMS)
                    }
                    else{
                        buttonAuth.isEnabled = validations.codeLenghtChecker(codeSMS)
                    }
                }

            })
            //---

            //onClickListener function to listen for  ask for check authorization code
            buttonAuth.setOnClickListener { view: View ->
                view.findNavController().navigate(R.id.action_EKTPForgottenPassAuthorizationCodeFragment_to_EKTPForgottenPassNewPassFragment)
            }
            //---

            //onClickListener on appBar BackButton to popBackStack fragment to earlier
            backAppbarButton.setOnClickListener { view : View ->
                findNavController().popBackStack()
            }
            //---

            return root
        }
    }
}