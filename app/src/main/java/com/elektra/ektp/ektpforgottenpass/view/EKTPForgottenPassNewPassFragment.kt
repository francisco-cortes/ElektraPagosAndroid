package com.elektra.ektp.ektpforgottenpass.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.elektra.ektp.R
import com.elektra.ektp.databinding.FragmentEktpForgottenPassNewPassBinding
import com.elektra.ektp.uservalidations.UserValidations

class EKTPForgottenPassNewPassFragment : Fragment() {

    private lateinit var binding: FragmentEktpForgottenPassNewPassBinding
    private val validations = UserValidations()
    private var showPassVar = false
    private var showPassVar2 = false
    private var passTextVar = ""
    private var passTextVar2 = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_ektp_forgotten_pass_new_pass, container, false)

        binding.buttonNewPass.isEnabled = false
        binding.matchPass.isVisible = false

        binding.insertNewPass.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                passTextVar = s.toString()
                if(passTextVar.length < 8 ){
                    binding.notMinIcon.isVisible = true
                    binding.okMinIcon.isVisible = false
                    binding.buttonNewPass.isEnabled = false
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
                    binding.buttonNewPass.isEnabled = false
                }
                if (validations.checkBankString(passTextVar)){
                    binding.okNameIcon.isVisible = true
                    binding.notNameIcon.isVisible = false
                }
                else{
                    binding.okNameIcon.isVisible = false
                    binding.notNameIcon.isVisible = true
                    binding.buttonNewPass.isEnabled = false
                }
                if (validations.checkConsecutiveString(passTextVar)){
                    binding.okMConsecutiveIcon.isVisible = true
                    binding.notConsecutiveIcon.isVisible = false
                }
                else{
                    binding.okMConsecutiveIcon.isVisible = false
                    binding.notConsecutiveIcon.isVisible = true
                    binding.buttonNewPass.isEnabled = false
                }
                if(passTextVar.length in 8..14 ){
                    binding.okMaxIcon.isVisible = true
                    binding.notMaxIcon.isVisible = false
                }
                else{
                    binding.okMaxIcon.isVisible = false
                    binding.notMaxIcon.isVisible = true
                    binding.buttonNewPass.isEnabled = false
                }
                if (passTextVar.length in 8..14 && validations.checkRepeatedChars(passTextVar) && validations.checkBankString(passTextVar)
                    && validations.checkConsecutiveString(passTextVar)){
                    binding.insertNewPass.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                }
                else{
                    binding.insertNewPass.setBackgroundResource(R.drawable.validation_edit_text)
                    binding.buttonNewPass.isEnabled = false
                }
            }

            override fun afterTextChanged(s: Editable?) {
                passTextVar = s.toString()
                if (passTextVar.length in 8..14){
                    if (passTextVar != passTextVar2){
                        binding.matchPass.isVisible = true
                        binding.notMatchesIcon.isVisible = true
                        binding.buttonNewPass.isEnabled = false
                    }
                    else{
                        binding.notMatchesIcon.isVisible = false
                        binding.matchPass.isVisible = false
                        binding.buttonNewPass.isEnabled = (passTextVar.length in 8..14 && validations.checkRepeatedChars(passTextVar) && validations.checkBankString(passTextVar)
                                && validations.checkConsecutiveString(passTextVar))
                    }
                }
            }
        })

        return binding.root
    }

}