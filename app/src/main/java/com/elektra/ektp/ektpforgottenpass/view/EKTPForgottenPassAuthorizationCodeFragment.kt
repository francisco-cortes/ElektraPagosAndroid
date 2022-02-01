package com.elektra.ektp.ektpforgottenpass.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.elektra.ektp.R
import com.elektra.ektp.databinding.FragmentEktpForgottenPassAuthorizationCodeBinding
import com.elektra.ektp.databinding.FragmentEktpForgottenPassPhoneNumberBinding
import com.elektra.ektp.ektpforgottenpass.view.viewmodel.EKTPForgottenPassAuthorizationCodeViewModel
import com.elektra.ektp.uservalidations.UserValidations

class EKTPForgottenPassAuthorizationCodeFragment : Fragment() {

    private lateinit var binding: FragmentEktpForgottenPassAuthorizationCodeBinding
    private val authorizationViewModel: EKTPForgottenPassAuthorizationCodeViewModel by viewModels()
    val validations = UserValidations()

    private lateinit var codeSMS: String
    private var codechar1 = ""
    private var codechar2 = ""
    private var codechar3 = ""
    private var codechar4 = ""
    private var codechar5 = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
        R.layout.fragment_ektp_forgotten_pass_authorization_code, container, false)

        binding.buttonAuth.isEnabled = false

        binding.verificationNumber1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                codechar1 = s.toString()
                codeSMS = validations.concatenaterCode(
                    codechar1, codechar2, codechar3, codechar4, codechar5
                )
                if (!codechar1.isNullOrBlank() && codechar1.length == 1) {
                    binding.buttonAuth.isEnabled = validations.codeLenghtChecker(codeSMS)
                    binding.verificationNumber2.requestFocus()
                }
                else{
                    binding.buttonAuth.isEnabled = false
                }
            }

            override fun afterTextChanged(s: Editable?) {
                codechar1 = s.toString()
                codeSMS = validations.concatenaterCode(
                    codechar1, codechar2, codechar3, codechar4, codechar5
                )
                if (!codechar1.isNullOrBlank() && codechar1.length == 1) {
                    binding.buttonAuth.isEnabled = validations.codeLenghtChecker(codeSMS)
                    binding.verificationNumber2.requestFocus()
                }
                else{
                    binding.buttonAuth.isEnabled = validations.codeLenghtChecker(codeSMS)
                }
            }

        })

        binding.verificationNumber2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                codechar2 = s.toString()
                codeSMS = validations.concatenaterCode(
                    codechar1, codechar2, codechar3, codechar4, codechar5
                )
                if (!codechar2.isNullOrBlank() && codechar2.length == 1) {
                    binding.buttonAuth.isEnabled = validations.codeLenghtChecker(codeSMS)
                    binding.verificationNumber3.requestFocus()
                }
                else{
                    binding.buttonAuth.isEnabled = false
                }
            }

            override fun afterTextChanged(s: Editable?) {
                codechar2 = s.toString()
                codeSMS = validations.concatenaterCode(
                    codechar1, codechar2, codechar3, codechar4, codechar5
                )
                if (!codechar2.isNullOrBlank() && codechar2.length == 1) {
                    binding.buttonAuth.isEnabled = validations.codeLenghtChecker(codeSMS)
                    binding.verificationNumber3.requestFocus()
                }
                else{
                    binding.buttonAuth.isEnabled = validations.codeLenghtChecker(codeSMS)
                }
            }

        })

        binding.verificationNumber3.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                codechar3 = s.toString()
                codeSMS = validations.concatenaterCode(
                    codechar1, codechar2, codechar3, codechar4, codechar5
                )
                if (!codechar3.isNullOrBlank() && codechar3.length == 1) {
                    binding.buttonAuth.isEnabled = validations.codeLenghtChecker(codeSMS)
                    binding.verificationNumber4.requestFocus()
                }
                else{
                    binding.buttonAuth.isEnabled = false
                }
            }

            override fun afterTextChanged(s: Editable?) {
                codechar3 = s.toString()
                codeSMS = validations.concatenaterCode(
                    codechar1, codechar2, codechar3, codechar4, codechar5
                )
                if (!codechar3.isNullOrBlank() && codechar3.length == 1) {
                    binding.buttonAuth.isEnabled = validations.codeLenghtChecker(codeSMS)
                    binding.verificationNumber4.requestFocus()
                }
                else{
                    binding.buttonAuth.isEnabled = validations.codeLenghtChecker(codeSMS)
                }
            }

        })

        binding.verificationNumber4.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                codechar4 = s.toString()
                codeSMS = validations.concatenaterCode(
                    codechar1, codechar2, codechar3, codechar4, codechar5
                )
                if (!codechar4.isNullOrBlank() && codechar4.length == 1) {
                    binding.buttonAuth.isEnabled = validations.codeLenghtChecker(codeSMS)
                    binding.verificationNumber5.requestFocus()
                }
                else{
                    binding.buttonAuth.isEnabled = false
                }
            }

            override fun afterTextChanged(s: Editable?) {
                codechar4 = s.toString()
                codeSMS = validations.concatenaterCode(
                    codechar1, codechar2, codechar3, codechar4, codechar5
                )
                if (!codechar4.isNullOrBlank() && codechar4.length == 1) {
                    binding.buttonAuth.isEnabled = validations.codeLenghtChecker(codeSMS)
                    binding.verificationNumber5.requestFocus()
                }
                else{
                    binding.buttonAuth.isEnabled = validations.codeLenghtChecker(codeSMS)
                }
            }

        })

        return binding.root
    }
}