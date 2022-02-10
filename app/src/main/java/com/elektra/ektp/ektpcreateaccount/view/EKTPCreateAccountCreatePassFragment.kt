package com.elektra.ektp.ektpcreateaccount.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.elektra.ektp.R
import com.elektra.ektp.databinding.FragmentEktpCreateAccountCreatePassBinding
import com.elektra.ektp.ektpsharedpreferences.EKTPUserApplication.Companion.preferences
import com.elektra.ektp.uservalidations.UserValidations

class EKTPCreateAccountCreatePassFragment : Fragment() {

    private lateinit var binding: FragmentEktpCreateAccountCreatePassBinding
    private val validations = UserValidations()
    private var showPassVar = false
    private var showPassVar2 = false
    private var passTextVar = ""
    private var passTextVar2 = ""
    private val checkBiometricStatus = preferences.getBioStatus()

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
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_ektp_create_account_create_pass, container, false)

        binding.button7.isEnabled = false
        binding.matchPass.isVisible = false

        binding.insertPass.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                passTextVar = s.toString()
                if(passTextVar.length < 8 ){
                    binding.notMinIcon.isVisible = true
                    binding.okMinIcon.isVisible = false
                    binding.button7.isEnabled = false
                }
                else{
                    binding.okMinIcon.isVisible = true
                    binding.notMinIcon.isVisible = false
                }
                if (validations.checkRepeatedChars(passTextVar)){
                    binding.okRepeatedIcon.isVisible = true
                    binding.notRepeatedIcon.isVisible = false
                }
                else{
                    binding.okRepeatedIcon.isVisible = false
                    binding.notRepeatedIcon.isVisible = true
                    binding.button7.isEnabled = false
                }
                if (validations.checkBankString(passTextVar)){
                    binding.okNameIcon.isVisible = true
                    binding.notNameIcon.isVisible = false
                }
                else{
                    binding.okNameIcon.isVisible = false
                    binding.notNameIcon.isVisible = true
                    binding.button7.isEnabled = false
                }
                if (validations.checkConsecutiveString(passTextVar)){
                    binding.okMConsecutiveIcon.isVisible = true
                    binding.notConsecutiveIcon.isVisible = false
                }
                else{
                    binding.okMConsecutiveIcon.isVisible = false
                    binding.notConsecutiveIcon.isVisible = true
                    binding.button7.isEnabled = false
                }
                if(passTextVar.length in 8..14 ){
                    binding.okMaxIcon.isVisible = true
                    binding.notMaxIcon.isVisible = false
                }
                else{
                    binding.okMaxIcon.isVisible = false
                    binding.notMaxIcon.isVisible = true
                    binding.button7.isEnabled = false
                }
                if (passTextVar.length in 8..14 && validations.checkRepeatedChars(passTextVar) && validations.checkBankString(passTextVar)
                    && validations.checkConsecutiveString(passTextVar)){
                    binding.insertPass.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                }
                else{
                    binding.insertPass.setBackgroundResource(R.drawable.validation_edit_text)
                    binding.button7.isEnabled = false
                }
            }

            override fun afterTextChanged(s: Editable?) {
                passTextVar = s.toString()
                if (passTextVar.length in 8..14){
                    if (passTextVar != passTextVar2){
                        binding.matchPass.isVisible = true
                        binding.notMatchesIcon.isVisible = true
                        binding.button7.isEnabled = false
                    }
                    else{
                        binding.notMatchesIcon.isVisible = false
                        binding.matchPass.isVisible = false
                        binding.button7.isEnabled = (passTextVar.length in 8..14 && validations.checkRepeatedChars(passTextVar) && validations.checkBankString(passTextVar)
                                && validations.checkConsecutiveString(passTextVar))
                    }
                }
            }
        })

        binding.insertConfirmPass.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                passTextVar2 = s.toString()
                if (passTextVar.length in 8..14){
                    if (passTextVar != passTextVar2){
                        binding.insertConfirmPass.setBackgroundResource(R.drawable.validation_edit_text)
                        binding.notMatchesIcon.isVisible = true
                        binding.matchPass.isVisible = true
                        binding.button7.isEnabled = false
                    }
                    else{
                        binding.insertConfirmPass.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                        binding.notMatchesIcon.isVisible = false
                        binding.matchPass.isVisible = false
                        binding.button7.isEnabled = (passTextVar.length in 8..14 && validations.checkRepeatedChars(passTextVar) && validations.checkBankString(passTextVar)
                                && validations.checkConsecutiveString(passTextVar))
                    }
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                passTextVar2 = s.toString()
                if (passTextVar.length in 8..14){
                    if (passTextVar != passTextVar2){
                        binding.insertConfirmPass.setBackgroundResource(R.drawable.validation_edit_text)
                        binding.notMatchesIcon.isVisible = true
                        binding.matchPass.isVisible = true
                        binding.button7.isEnabled = false
                    }
                    else{
                        binding.insertConfirmPass.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                        binding.notMatchesIcon.isVisible = false
                        binding.matchPass.isVisible = false
                        binding.button7.isEnabled = (passTextVar.length in 8..14 && validations.checkRepeatedChars(passTextVar) && validations.checkBankString(passTextVar)
                                && validations.checkConsecutiveString(passTextVar))
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {
                passTextVar2 = s.toString()
                if (passTextVar.length in 8..14){
                    if (passTextVar != passTextVar2){
                        binding.insertConfirmPass.setBackgroundResource(R.drawable.validation_edit_text)
                        binding.notMatchesIcon.isVisible = true
                        binding.matchPass.isVisible = true
                        binding.button7.isEnabled = false
                    }
                    else{
                        binding.insertConfirmPass.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                        binding.notMatchesIcon.isVisible = false
                        binding.matchPass.isVisible = false
                        binding.button7.isEnabled = (passTextVar.length in 8..14 && validations.checkRepeatedChars(passTextVar) && validations.checkBankString(passTextVar)
                                && validations.checkConsecutiveString(passTextVar))
                    }
                }
            }
        })

        binding.showPassButton1.setOnClickListener {

            if (!showPassVar) {
                binding.insertPass.transformationMethod = HideReturnsTransformationMethod.getInstance()
                binding.showPassButton1.setBackgroundResource(R.drawable.ic_hide_pass)
                showPassVar = true
            }
            else {
                binding.insertPass.transformationMethod = PasswordTransformationMethod.getInstance()
                binding.showPassButton1.setBackgroundResource(R.drawable.ic_show_pass)
                showPassVar = false
            }
        }

        binding.showPassButton2.setOnClickListener {

            if (!showPassVar2) {
                binding.insertConfirmPass.transformationMethod = HideReturnsTransformationMethod.getInstance()
                binding.showPassButton2.setBackgroundResource(R.drawable.ic_hide_pass)
                showPassVar2 = true
            }
            else {
                binding.insertConfirmPass.transformationMethod = PasswordTransformationMethod.getInstance()
                binding.showPassButton2.setBackgroundResource(R.drawable.ic_show_pass)
                showPassVar2 = false
            }
        }

        binding.backAppbarButton.setOnClickListener { view: View ->
            findNavController().popBackStack()
        }

        binding.button7.setOnClickListener { view: View ->
            if (checkBiometricStatus ==1 ){
                view.findNavController().navigate(R.id.action_EKTPCreateAccountCreatePassFragment_to_EKTPCreateAccountBiometricsActivationFragment)
            }
            else{
                view.findNavController().navigate(R.id.action_EKTPCreateAccountCreatePassFragment_to_EKTPCreateAccountSuccessfulFragment)
            }

        }

        return binding.root
    }

}