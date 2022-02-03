package com.elektra.ektp.ektpcreateaccount.view

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
import com.elektra.ektp.databinding.FragmentEktpCreateAccountSmsVerificationBinding
import com.elektra.ektp.ektpcreateaccount.viewmodel.EKTPCreateAccountSMSVerificationViewModel
import com.elektra.ektp.uservalidations.UserValidations

class EKTPCreateAccountSMSVerificationFragment : Fragment() {

    private val verificationCodeViewModel: EKTPCreateAccountSMSVerificationViewModel by viewModels()
    val validations = UserValidations()
    private val smsVerificationViewModel: EKTPCreateAccountSMSVerificationViewModel by viewModels()

    private lateinit var codeSMS: String
    private var codechar1 = ""
    private var codechar2 = ""
    private var codechar3 = ""
    private var codechar4 = ""
    private var codechar5 = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        val binding = DataBindingUtil.inflate<FragmentEktpCreateAccountSmsVerificationBinding>(inflater,
            R.layout.fragment_ektp_create_account_sms_verification, container, false)

        binding.smsContinueButton.isEnabled = false
        binding.invalidSMSTextView.isVisible = false

        binding.verificationNumber1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                codechar1 = s.toString()
                binding.verificationNumber1.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                binding.verificationNumber2.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                binding.verificationNumber3.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                binding.verificationNumber4.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                binding.verificationNumber5.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                binding.invalidSMSTextView.isVisible = false
                codeSMS = validations.concatenaterCode(
                    codechar1, codechar2, codechar3, codechar4, codechar5
                )
                if (!codechar1.isNullOrBlank() && codechar1.length == 1) {
                    binding.smsContinueButton.isEnabled = validations.codeLenghtChecker(codeSMS)
                    binding.verificationNumber2.requestFocus()
                }
                else{
                    binding.smsContinueButton.isEnabled = false
                }
            }

            override fun afterTextChanged(s: Editable?) {
                codechar1 = s.toString()
                binding.verificationNumber1.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                binding.verificationNumber2.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                binding.verificationNumber3.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                binding.verificationNumber4.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                binding.verificationNumber5.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                binding.invalidSMSTextView.isVisible = false
                codeSMS = validations.concatenaterCode(
                    codechar1, codechar2, codechar3, codechar4, codechar5
                )
                if (!codechar1.isNullOrBlank() && codechar1.length == 1) {
                    binding.smsContinueButton.isEnabled = validations.codeLenghtChecker(codeSMS)
                    binding.verificationNumber2.requestFocus()
                }
                else{
                    binding.smsContinueButton.isEnabled = validations.codeLenghtChecker(codeSMS)
                }
            }

        })

        binding.verificationNumber2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                codechar2 = s.toString()
                binding.verificationNumber1.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                binding.verificationNumber2.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                binding.verificationNumber3.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                binding.verificationNumber4.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                binding.verificationNumber5.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                binding.invalidSMSTextView.isVisible = false
                codeSMS = validations.concatenaterCode(
                    codechar1, codechar2, codechar3, codechar4, codechar5
                )
                if (!codechar2.isNullOrBlank() && codechar2.length == 1) {
                    binding.smsContinueButton.isEnabled = validations.codeLenghtChecker(codeSMS)
                    binding.verificationNumber3.requestFocus()
                }
                else{
                    binding.smsContinueButton.isEnabled = false
                }
            }

            override fun afterTextChanged(s: Editable?) {
                codechar2 = s.toString()
                binding.verificationNumber1.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                binding.verificationNumber2.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                binding.verificationNumber3.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                binding.verificationNumber4.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                binding.verificationNumber5.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                binding.invalidSMSTextView.isVisible = false
                codeSMS = validations.concatenaterCode(
                    codechar1, codechar2, codechar3, codechar4, codechar5
                )
                if (!codechar2.isNullOrBlank() && codechar2.length == 1) {
                    binding.smsContinueButton.isEnabled = validations.codeLenghtChecker(codeSMS)
                    binding.verificationNumber3.requestFocus()
                }
                else{
                    binding.smsContinueButton.isEnabled = validations.codeLenghtChecker(codeSMS)
                }
            }

        })

        binding.verificationNumber3.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                codechar3 = s.toString()
                binding.verificationNumber1.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                binding.verificationNumber2.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                binding.verificationNumber3.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                binding.verificationNumber4.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                binding.verificationNumber5.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                binding.invalidSMSTextView.isVisible = false
                codeSMS = validations.concatenaterCode(
                    codechar1, codechar2, codechar3, codechar4, codechar5
                )
                if (!codechar3.isNullOrBlank() && codechar3.length == 1) {
                    binding.smsContinueButton.isEnabled = validations.codeLenghtChecker(codeSMS)
                    binding.verificationNumber4.requestFocus()
                }
                else{
                    binding.smsContinueButton.isEnabled = false
                }
            }

            override fun afterTextChanged(s: Editable?) {
                codechar3 = s.toString()
                binding.verificationNumber1.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                binding.verificationNumber2.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                binding.verificationNumber3.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                binding.verificationNumber4.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                binding.verificationNumber5.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                binding.invalidSMSTextView.isVisible = false
                codeSMS = validations.concatenaterCode(
                    codechar1, codechar2, codechar3, codechar4, codechar5
                )
                if (!codechar3.isNullOrBlank() && codechar3.length == 1) {
                    binding.smsContinueButton.isEnabled = validations.codeLenghtChecker(codeSMS)
                    binding.verificationNumber4.requestFocus()
                }
                else{
                    binding.smsContinueButton.isEnabled = validations.codeLenghtChecker(codeSMS)
                }
            }

        })

        binding.verificationNumber4.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                codechar4 = s.toString()
                binding.verificationNumber1.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                binding.verificationNumber2.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                binding.verificationNumber3.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                binding.verificationNumber4.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                binding.verificationNumber5.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                binding.invalidSMSTextView.isVisible = false
                codeSMS = validations.concatenaterCode(
                    codechar1, codechar2, codechar3, codechar4, codechar5
                )
                if (!codechar4.isNullOrBlank() && codechar4.length == 1) {
                    binding.smsContinueButton.isEnabled = validations.codeLenghtChecker(codeSMS)
                    binding.verificationNumber5.requestFocus()
                }
                else{
                    binding.smsContinueButton.isEnabled = false
                }
            }

            override fun afterTextChanged(s: Editable?) {
                codechar4 = s.toString()
                binding.verificationNumber1.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                binding.verificationNumber2.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                binding.verificationNumber3.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                binding.verificationNumber4.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                binding.verificationNumber5.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                binding.invalidSMSTextView.isVisible = false
                codeSMS = validations.concatenaterCode(
                    codechar1, codechar2, codechar3, codechar4, codechar5
                )
                if (!codechar4.isNullOrBlank() && codechar4.length == 1) {
                    binding.smsContinueButton.isEnabled = validations.codeLenghtChecker(codeSMS)
                    binding.verificationNumber5.requestFocus()
                }
                else{
                    binding.smsContinueButton.isEnabled = validations.codeLenghtChecker(codeSMS)
                }
            }

        })

        binding.verificationNumber5.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                codechar5 = s.toString()
                binding.verificationNumber1.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                binding.verificationNumber2.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                binding.verificationNumber3.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                binding.verificationNumber4.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                binding.verificationNumber5.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                binding.invalidSMSTextView.isVisible = false
                codeSMS = validations.concatenaterCode(
                    codechar1, codechar2, codechar3, codechar4, codechar5
                )
                if (!codechar5.isNullOrBlank() && codechar5.length == 1) {
                    binding.smsContinueButton.isEnabled = validations.codeLenghtChecker(codeSMS)
                }
                else{
                    binding.smsContinueButton.isEnabled = false
                }
            }

            override fun afterTextChanged(s: Editable?) {
                codechar5 = s.toString()
                binding.verificationNumber1.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                binding.verificationNumber2.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                binding.verificationNumber3.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                binding.verificationNumber4.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                binding.verificationNumber5.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                binding.invalidSMSTextView.isVisible = false
                codeSMS = validations.concatenaterCode(
                    codechar1, codechar2, codechar3, codechar4, codechar5
                )
                if (!codechar5.isNullOrBlank() && codechar5.length == 1) {
                    binding.smsContinueButton.isEnabled = validations.codeLenghtChecker(codeSMS)
                }
                else{
                    binding.smsContinueButton.isEnabled = validations.codeLenghtChecker(codeSMS)
                }
            }

        })

        binding.smsContinueButton.setOnClickListener { view: View ->
            if (!smsVerificationViewModel.checkSMSVerification(codeSMS)){
                view.findNavController().navigate(R.id.action_EKTPCreateAccountSMSVerificationFragment_to_EKPTCreateAccountRegisterFormFragment)
            }
            else{
                binding.verificationNumber1.setBackgroundResource(R.drawable.validation_edit_text)
                binding.verificationNumber2.setBackgroundResource(R.drawable.validation_edit_text)
                binding.verificationNumber3.setBackgroundResource(R.drawable.validation_edit_text)
                binding.verificationNumber4.setBackgroundResource(R.drawable.validation_edit_text)
                binding.verificationNumber5.setBackgroundResource(R.drawable.validation_edit_text)
                binding.invalidSMSTextView.isVisible = true
            }

        }

        binding.backAppbarButton.setOnClickListener { view : View ->
            findNavController().popBackStack()
        }

        return  binding.root
    }

}