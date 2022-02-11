package com.elektra.ektp.ektpforgottenpass.view

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
import com.elektra.ektp.databinding.FragmentEktpForgottenPassNewPassBinding
import com.elektra.ektp.ektptoaster.EKTPToaster
import com.elektra.ektp.uservalidations.UserValidations

class EKTPForgottenPassNewPassFragment : Fragment(){

    //Global databinding access variable
    private lateinit var binding: FragmentEktpForgottenPassNewPassBinding
    //UserValidations access variable
    private val validations = UserValidations()
    //General data variables
    private var showPassVar = false
    private var showPassVar2 = false
    private var passTextVar = ""
    private var passTextVar2 = ""

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
            R.layout.fragment_ektp_forgotten_pass_new_pass, container, false)

        //Wrap this block code for all the lines with binding variable
        with(binding){
            buttonNewPass.isEnabled = false
            matchPass.isVisible = false

            //TextWatcher function to listen for changes on editText
            insertNewPass.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    passTextVar = s.toString()
                    if (validations.checkRepeatedChars(passTextVar)){
                        okRepeatedIcon.isVisible = true
                        notRepeatedIcon.isVisible = false
                    }
                    else{
                        okRepeatedIcon.isVisible = false
                        notRepeatedIcon.isVisible = true
                        buttonNewPass.isEnabled = false
                    }
                    if (validations.checkBankString(passTextVar)){
                        okNameIcon.isVisible = true
                        notNameIcon.isVisible = false
                    }
                    else{
                        okNameIcon.isVisible = false
                        notNameIcon.isVisible = true
                        buttonNewPass.isEnabled = false
                    }
                    if (validations.checkConsecutiveString(passTextVar)){
                        okMConsecutiveIcon.isVisible = true
                        notConsecutiveIcon.isVisible = false
                    }
                    else{
                        okMConsecutiveIcon.isVisible = false
                        notConsecutiveIcon.isVisible = true
                        buttonNewPass.isEnabled = false
                    }
                    if(passTextVar.length in 8..14 ){
                        okMaxIcon.isVisible = true
                        notMaxIcon.isVisible = false
                    }
                    else{
                        okMaxIcon.isVisible = false
                        notMaxIcon.isVisible = true
                        buttonNewPass.isEnabled = false
                    }
                    if (passTextVar.length in 8..14 && validations.checkRepeatedChars(passTextVar) && validations.checkBankString(passTextVar)
                        && validations.checkConsecutiveString(passTextVar)){
                        insertNewPass.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    }
                    else{
                        insertNewPass.setBackgroundResource(R.drawable.validation_edit_text)
                        buttonNewPass.isEnabled = false
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    passTextVar = s.toString()
                    if (passTextVar.length in 8..14){
                        if (passTextVar != passTextVar2){
                            matchPass.isVisible = true
                            notMatchesIcon.isVisible = true
                            buttonNewPass.isEnabled = false
                        }
                        else{
                            notMatchesIcon.isVisible = false
                            matchPass.isVisible = false
                            buttonNewPass.isEnabled = (passTextVar.length in 8..14 && validations.checkRepeatedChars(passTextVar) && validations.checkBankString(passTextVar)
                                    && validations.checkConsecutiveString(passTextVar))
                        }
                    }
                }
            })
            //---

            //TextWatcher function to listen for changes on editText
            insertConfirmPass.addTextChangedListener(object: TextWatcher{
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    passTextVar2 = s.toString()
                    if (passTextVar.length in 8..14){
                        if (passTextVar != passTextVar2){
                            insertConfirmPass.setBackgroundResource(R.drawable.validation_edit_text)
                            notMatchesIcon.isVisible = true
                            matchPass.isVisible = true
                            buttonNewPass.isEnabled = false
                        }
                        else{
                            insertConfirmPass.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                            notMatchesIcon.isVisible = false
                            matchPass.isVisible = false
                            buttonNewPass.isEnabled = (passTextVar.length in 8..14 && validations.checkRepeatedChars(passTextVar) && validations.checkBankString(passTextVar)
                                    && validations.checkConsecutiveString(passTextVar))
                        }
                    }
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    passTextVar2 = s.toString()
                    if (passTextVar.length in 8..14){
                        if (passTextVar != passTextVar2){
                            insertConfirmPass.setBackgroundResource(R.drawable.validation_edit_text)
                            notMatchesIcon.isVisible = true
                            matchPass.isVisible = true
                            buttonNewPass.isEnabled = false
                        }
                        else{
                            insertConfirmPass.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                            notMatchesIcon.isVisible = false
                            matchPass.isVisible = false
                            buttonNewPass.isEnabled = (passTextVar.length in 8..14 && validations.checkRepeatedChars(passTextVar) && validations.checkBankString(passTextVar)
                                    && validations.checkConsecutiveString(passTextVar))
                        }
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    passTextVar2 = s.toString()
                    if (passTextVar.length in 8..14){
                        if (passTextVar != passTextVar2){
                            insertConfirmPass.setBackgroundResource(R.drawable.validation_edit_text)
                            notMatchesIcon.isVisible = true
                            matchPass.isVisible = true
                            buttonNewPass.isEnabled = false
                        }
                        else{
                            insertConfirmPass.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                            notMatchesIcon.isVisible = false
                            matchPass.isVisible = false
                            buttonNewPass.isEnabled = (passTextVar.length in 8..14 && validations.checkRepeatedChars(passTextVar) && validations.checkBankString(passTextVar)
                                    && validations.checkConsecutiveString(passTextVar))
                        }
                    }
                }
            })
            //---

            //onClickListener on show/hidePass to listen for user interaction with password visibility
            showPassButton1.setOnClickListener {

                if (!showPassVar) {
                    insertNewPass.transformationMethod = HideReturnsTransformationMethod.getInstance()
                    showPassButton1.setBackgroundResource(R.drawable.ic_hide_pass)
                    showPassVar = true
                }
                else {
                    insertNewPass.transformationMethod = PasswordTransformationMethod.getInstance()
                    showPassButton1.setBackgroundResource(R.drawable.ic_show_pass)
                    showPassVar = false
                }
            }
            //---

            //onClickListener on show/hidePassConfirmation to listen for user interaction with password visibility
            showPassButton2.setOnClickListener {

                if (!showPassVar2) {
                    insertConfirmPass.transformationMethod = HideReturnsTransformationMethod.getInstance()
                    showPassButton2.setBackgroundResource(R.drawable.ic_hide_pass)
                    showPassVar2 = true
                }
                else {
                    insertConfirmPass.transformationMethod = PasswordTransformationMethod.getInstance()
                    showPassButton2.setBackgroundResource(R.drawable.ic_show_pass)
                    showPassVar2 = false
                }
            }
            //---

            //onClickListener on appBar BackButton to popBackStack fragment
            backAppbarButton.setOnClickListener { view : View ->
                view.findNavController().popBackStack()
            }
            //---

            //onClickListener on continueButton to navigate to biometricsActivation or Successful createAccount according to sharedPreferences
            buttonNewPass.setOnClickListener { view: View ->
                view.findNavController().navigate(R.id.action_EKTPForgottenPassNewPassFragment_to_EKTPForgottenPassSuccessfulFragment)
            }
            //---

            return root
        }
    }
}