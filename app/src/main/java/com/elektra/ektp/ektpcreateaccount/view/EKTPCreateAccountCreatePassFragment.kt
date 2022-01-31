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
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.elektra.ektp.R
import com.elektra.ektp.databinding.FragmentEktpCreateAccountCreatePassBinding
import com.elektra.ektp.ektpsharedpreferences.EKPTUserApplication.Companion.preferences
import com.elektra.ektp.uservalidations.UserValidations

class EKTPCreateAccountCreatePassFragment : Fragment() {

    private lateinit var binding: FragmentEktpCreateAccountCreatePassBinding
    private val validations = UserValidations()
    private var showPassVar = false
    private var showPassVar2 = false
    private var passTextVar = ""
    private var passTextVar2 = ""
    private val checkBiometricStatus = preferences.getBioStatus()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentEktpCreateAccountCreatePassBinding>(inflater,
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
                if(passTextVar.length in 8..14 ){
                    binding.okMaxIcon.isVisible = true
                    binding.notMaxIcon.isVisible = false
                }
                else{
                    binding.okMaxIcon.isVisible = false
                    binding.notMaxIcon.isVisible = true
                    binding.button7.isEnabled = false
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
            }

            override fun afterTextChanged(s: Editable?) {
                passTextVar = s.toString()
                if (passTextVar.length in 8..14){
                    if (passTextVar != passTextVar2){
                        binding.notMatchesIcon.isVisible = true
                        binding.matchPass.isVisible = true
                        binding.button7.isEnabled = false
                    }
                    else{
                        binding.notMatchesIcon.isVisible = false
                        binding.matchPass.isVisible = false
                        binding.button7.isEnabled = true
                    }
                }
            }
        })

        binding.insertConfirmPass.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                passTextVar2 = s.toString()
                if (passTextVar.length in 8..14){
                    if (passTextVar != passTextVar2){
                        binding.notMatchesIcon.isVisible = true
                        binding.matchPass.isVisible = true
                        binding.button7.isEnabled = false
                    }
                    else{
                        binding.notMatchesIcon.isVisible = false
                        binding.matchPass.isVisible = false
                        binding.button7.isEnabled = true
                    }
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                passTextVar2 = s.toString()
                if (passTextVar.length in 8..14){
                    if (passTextVar != passTextVar2){
                        binding.notMatchesIcon.isVisible = true
                        binding.matchPass.isVisible = true
                        binding.button7.isEnabled = false
                    }
                    else{
                        binding.notMatchesIcon.isVisible = false
                        binding.matchPass.isVisible = false
                        binding.button7.isEnabled = true
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

        binding.showPassButton1.setOnClickListener {

            if (!showPassVar) {
                binding.insertPass.transformationMethod = HideReturnsTransformationMethod.getInstance()
                showPassVar = true
            }
            else {
                binding.insertPass.transformationMethod = PasswordTransformationMethod.getInstance()
                showPassVar = false
            }
        }

        binding.showPassButton2.setOnClickListener {

            if (!showPassVar2) {
                binding.insertConfirmPass.transformationMethod = HideReturnsTransformationMethod.getInstance()
                showPassVar2 = true
            }
            else {
                binding.insertConfirmPass.transformationMethod = PasswordTransformationMethod.getInstance()
                showPassVar2 = false
            }
        }

        return binding.root
    }

}