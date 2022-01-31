package com.elektra.ektp.ektpcreateaccount.view

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
import com.elektra.ektp.databinding.FragmentEktpCreateAccountSmsVerificationBinding
import com.elektra.ektp.ektpcreateaccount.viewmodel.EKTPCreateAccountSMSVerificationViewModel

class EKTPCreateAccountSMSVerificationFragment : Fragment() {

    private val verificationCodeViewModel: EKTPCreateAccountSMSVerificationViewModel by viewModels()
    private lateinit var codeSMS: String
    private lateinit var codechar1: String
    private lateinit var codechar2: String
    private lateinit var codechar3: String
    private lateinit var codechar4: String
    private lateinit var codechar5: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentEktpCreateAccountSmsVerificationBinding>(inflater,
            R.layout.fragment_ektp_create_account_sms_verification, container, false)

        binding.smsContinueButton.isEnabled = false

        binding.verificationNumber1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                codechar1 = s.toString()
                codeSMS = verificationCodeViewModel.concatenaterCode(
                    codechar1, codechar2, codechar3, codechar4, codechar5
                )
                if (!codechar1.isNullOrBlank() && codechar1.length == 1) {
                    binding.smsContinueButton.isEnabled = verificationCodeViewModel.codeLenghtChecker(codeSMS)
                    binding.verificationNumber2.requestFocus()
                }
                else{
                    binding.smsContinueButton.isEnabled = false
                }
            }

            override fun afterTextChanged(s: Editable?) {
                codechar1 = s.toString()
                codeSMS = verificationCodeViewModel.concatenaterCode(
                    codechar1, codechar2, codechar3, codechar4, codechar5
                )
                if (!codechar1.isNullOrBlank() && codechar1.length == 1) {
                    binding.smsContinueButton.isEnabled = verificationCodeViewModel.codeLenghtChecker(codeSMS)
                    binding.verificationNumber2.requestFocus()
                }
                else{
                    binding.smsContinueButton.isEnabled = verificationCodeViewModel.codeLenghtChecker(codeSMS)
                }
            }

        })

        binding.verificationNumber2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                codechar2 = s.toString()
                codeSMS = verificationCodeViewModel.concatenaterCode(
                    codechar1, codechar2, codechar3, codechar4, codechar5
                )
                if (!codechar2.isNullOrBlank() && codechar2.length == 1) {
                    binding.smsContinueButton.isEnabled = verificationCodeViewModel.codeLenghtChecker(codeSMS)
                    binding.verificationNumber3.requestFocus()
                }
                else{
                    binding.smsContinueButton.isEnabled = false
                }
            }

            override fun afterTextChanged(s: Editable?) {
                codechar2 = s.toString()
                codeSMS = verificationCodeViewModel.concatenaterCode(
                    codechar1, codechar2, codechar3, codechar4, codechar5
                )
                if (!codechar2.isNullOrBlank() && codechar2.length == 1) {
                    binding.smsContinueButton.isEnabled = verificationCodeViewModel.codeLenghtChecker(codeSMS)
                    binding.verificationNumber3.requestFocus()
                }
                else{
                    binding.smsContinueButton.isEnabled = verificationCodeViewModel.codeLenghtChecker(codeSMS)
                }
            }

        })

        binding.verificationNumber3.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                codechar3 = s.toString()
                codeSMS = verificationCodeViewModel.concatenaterCode(
                    codechar1, codechar2, codechar3, codechar4, codechar5
                )
                if (!codechar3.isNullOrBlank() && codechar3.length == 1) {
                    binding.smsContinueButton.isEnabled = verificationCodeViewModel.codeLenghtChecker(codeSMS)
                    binding.verificationNumber4.requestFocus()
                }
                else{
                    binding.smsContinueButton.isEnabled = false
                }
            }

            override fun afterTextChanged(s: Editable?) {
                codechar3 = s.toString()
                codeSMS = verificationCodeViewModel.concatenaterCode(
                    codechar1, codechar2, codechar3, codechar4, codechar5
                )
                if (!codechar3.isNullOrBlank() && codechar3.length == 1) {
                    binding.smsContinueButton.isEnabled = verificationCodeViewModel.codeLenghtChecker(codeSMS)
                    binding.verificationNumber4.requestFocus()
                }
                else{
                    binding.smsContinueButton.isEnabled = verificationCodeViewModel.codeLenghtChecker(codeSMS)
                }
            }

        })

        binding.verificationNumber4.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                codechar4 = s.toString()
                codeSMS = verificationCodeViewModel.concatenaterCode(
                    codechar1, codechar2, codechar3, codechar4, codechar5
                )
                if (!codechar4.isNullOrBlank() && codechar4.length == 1) {
                    binding.smsContinueButton.isEnabled = verificationCodeViewModel.codeLenghtChecker(codeSMS)
                    binding.verificationNumber5.requestFocus()
                }
                else{
                    binding.smsContinueButton.isEnabled = false
                }
            }

            override fun afterTextChanged(s: Editable?) {
                codechar4 = s.toString()
                codeSMS = verificationCodeViewModel.concatenaterCode(
                    codechar1, codechar2, codechar3, codechar4, codechar5
                )
                if (!codechar4.isNullOrBlank() && codechar4.length == 1) {
                    binding.smsContinueButton.isEnabled = verificationCodeViewModel.codeLenghtChecker(codeSMS)
                    binding.verificationNumber5.requestFocus()
                }
                else{
                    binding.smsContinueButton.isEnabled = verificationCodeViewModel.codeLenghtChecker(codeSMS)
                }
            }

        })

        return  binding.root
    }

}