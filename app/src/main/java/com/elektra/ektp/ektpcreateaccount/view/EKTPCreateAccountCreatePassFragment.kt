package com.elektra.ektp.ektpcreateaccount.view

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
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
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.elektra.ektp.R
import com.elektra.ektp.databinding.FragmentEktpCreateAccountCreatePassBinding
import com.elektra.ektp.ektpcreateaccount.viewmodel.EKTPCreateAccountActivityViewModel
import com.elektra.ektp.ektpcreateaccount.viewmodel.EKTPCreateAccountActivityViewModel.Companion.bPlace
import com.elektra.ektp.ektpcreateaccount.viewmodel.EKTPCreateAccountActivityViewModel.Companion.folio
import com.elektra.ektp.ektpcreateaccount.viewmodel.EKTPCreateAccountActivityViewModel.Companion.uNext
import com.elektra.ektp.ektpcreateaccount.viewmodel.EKTPCreateAccountActivityViewModel.Companion.uNint
import com.elektra.ektp.ektpcreateaccount.viewmodel.EKTPCreateAccountActivityViewModel.Companion.uPC
import com.elektra.ektp.ektpcreateaccount.viewmodel.EKTPCreateAccountActivityViewModel.Companion.uSett
import com.elektra.ektp.ektpcreateaccount.viewmodel.EKTPCreateAccountActivityViewModel.Companion.uStr
import com.elektra.ektp.ektpcreateaccount.viewmodel.EKTPCreateAccountCreatePassViewModel
import com.elektra.ektp.ektpsharedpreferences.EKTPUserApplication.Companion.preferences
import com.elektra.ektp.ektputilies.uservalidations.UserValidations
import kotlinx.coroutines.Job

class EKTPCreateAccountCreatePassFragment : Fragment() {

    //Global databinding access variable
    private lateinit var binding: FragmentEktpCreateAccountCreatePassBinding
    private lateinit var loadingLayout: View

    //ViewModel access variable
    private val validations = UserValidations()

    //Data variables
    private var showPassVar = false
    private var showPassVar2 = false
    private var passTextVar = ""
    private var passTextVar2 = ""

    //SharedPreferences variable
    private val checkBiometricStatus = preferences.getBioStatus()
    private val viewModel : EKTPCreateAccountCreatePassViewModel by viewModels()
    private val activityViewModel = EKTPCreateAccountActivityViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Overriding obBackPressed to popBackStack fragment
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

        //Wrap this block code for all the lines with binding variable
        with(binding){
            button7.isEnabled = false
            matchPass.isVisible = false

            //TextWatcher function to listen for changes on editText
            insertPass.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    passTextVar = s.toString()
                    if (passTextVar.length < 8){
                        notMinIcon.isVisible = true
                        okMinIcon.isVisible = false
                    }
                    else{
                        notMinIcon.isVisible = false
                        okMinIcon.isVisible = true
                    }
                    if (validations.checkRepeatedChars(passTextVar)){
                        okRepeatedIcon.isVisible = true
                        notRepeatedIcon.isVisible = false
                    }
                    else{
                        okRepeatedIcon.isVisible = false
                        notRepeatedIcon.isVisible = true
                        button7.isEnabled = false
                    }
                    if (validations.checkBankString(passTextVar)){
                        okNameIcon.isVisible = true
                        notNameIcon.isVisible = false
                    }
                    else{
                        okNameIcon.isVisible = false
                        notNameIcon.isVisible = true
                        button7.isEnabled = false
                    }
                    if (validations.checkConsecutiveString(passTextVar)){
                        okMConsecutiveIcon.isVisible = true
                        notConsecutiveIcon.isVisible = false
                    }
                    else{
                        okMConsecutiveIcon.isVisible = false
                        notConsecutiveIcon.isVisible = true
                        button7.isEnabled = false
                    }
                    if(passTextVar.length in 8..14 ){
                        okMaxIcon.isVisible = true
                        notMaxIcon.isVisible = false
                    }
                    else{
                        okMaxIcon.isVisible = false
                        notMaxIcon.isVisible = true
                        button7.isEnabled = false
                    }
                    if (passTextVar.length in 8..14 && validations.checkRepeatedChars(passTextVar) && validations.checkBankString(passTextVar)
                        && validations.checkConsecutiveString(passTextVar)){
                        insertPass.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    }
                    else{
                        insertPass.setBackgroundResource(R.drawable.validation_edit_text)
                        button7.isEnabled = false
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    passTextVar = s.toString()
                    if (passTextVar.length in 8..14){
                        if (passTextVar != passTextVar2){
                            matchPass.isVisible = true
                            notMatchesIcon.isVisible = true
                            button7.isEnabled = false
                            insertConfirmPass.setBackgroundResource(R.drawable.validation_edit_text)
                        }
                        else{
                            notMatchesIcon.isVisible = false
                            matchPass.isVisible = false
                            insertConfirmPass.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                            button7.isEnabled = (passTextVar.length in 8..14 && validations.checkRepeatedChars(passTextVar) && validations.checkBankString(passTextVar)
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
                            button7.isEnabled = false
                        }
                        else{
                            insertConfirmPass.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                            notMatchesIcon.isVisible = false
                            matchPass.isVisible = false
                            button7.isEnabled = (passTextVar.length in 8..14 && validations.checkRepeatedChars(passTextVar) && validations.checkBankString(passTextVar)
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
                            button7.isEnabled = false
                        }
                        else{
                            insertConfirmPass.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                            notMatchesIcon.isVisible = false
                            matchPass.isVisible = false
                            button7.isEnabled = (passTextVar.length in 8..14 && validations.checkRepeatedChars(passTextVar) && validations.checkBankString(passTextVar)
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
                            button7.isEnabled = false
                        }
                        else{
                            insertConfirmPass.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                            notMatchesIcon.isVisible = false
                            matchPass.isVisible = false
                            button7.isEnabled = (passTextVar.length in 8..14 && validations.checkRepeatedChars(passTextVar) && validations.checkBankString(passTextVar)
                                    && validations.checkConsecutiveString(passTextVar))
                        }
                    }
                }
            })
            //---

            //onClickListener on show/hidePass to listen for user interaction with password visibility
            showPassButton1.setOnClickListener {

                if (!showPassVar) {
                    insertPass.transformationMethod = HideReturnsTransformationMethod.getInstance()
                    showPassButton1.setBackgroundResource(R.drawable.ic_hide_pass)
                    showPassVar = true
                }
                else {
                    insertPass.transformationMethod = PasswordTransformationMethod.getInstance()
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
            backAppbarButton.setOnClickListener { view: View ->
                findNavController().popBackStack()
            }
            //---

            //onClickListener on continueButton to navigate to biometricsActivation or Successful createAccount according to sharedPreferences
            button7.setOnClickListener { view: View ->
                preferences.saveTemporalPassword(passTextVar)
                extraData(viewModel.apiDatosExtra(uPC, uSett, bPlace,preferences.getTemporalPassword(), folio, uStr, uNext, uNint, uSett))
            }
            //---

            return root
        }
    }

    private fun extraData(value: Job) {
        loadingLayout = layoutInflater.inflate(R.layout.loading_alert_layout,null)
        val loadingAlert = alertDialogOpener(loadingLayout, requireContext())
        loadingAlert.show()
        loadingAlert.getWindow()?.setLayout(250, 250)
        var canContinue = false
        var attempts = 0
        val timer = object : CountDownTimer(2000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                if(value.isCompleted){
                    if (viewModel.canContinue){
                        loadingAlert.dismiss()
                        if (checkBiometricStatus ==1 ){
                            view?.findNavController()?.navigate(R.id.action_EKTPCreateAccountCreatePassFragment_to_EKTPCreateAccountBiometricsActivationFragment)
                        }
                        else{
                            view?.findNavController()?.navigate(R.id.action_EKTPCreateAccountCreatePassFragment_to_EKTPCreateAccountSuccessfulFragment)
                        }
                        canContinue = true
                        cancel()
                    }else{
                        loadingAlert.dismiss()
                        canContinue = false
                        cancel()
                    }
                }
            }
            override fun onFinish() {
                if(value.isCompleted){
                    if (viewModel.canContinue){
                        loadingAlert.dismiss()
                        if (checkBiometricStatus ==1 ){
                            view?.findNavController()?.navigate(R.id.action_EKTPCreateAccountCreatePassFragment_to_EKTPCreateAccountBiometricsActivationFragment)
                        }
                        else{
                            view?.findNavController()?.navigate(R.id.action_EKTPCreateAccountCreatePassFragment_to_EKTPCreateAccountSuccessfulFragment)
                        }
                        canContinue = true
                        cancel()
                    }else{
                        loadingAlert.dismiss()
                        canContinue = false
                        cancel()
                    }
                }else{
                    attempts = +1
                    if (attempts>2){
                        start()
                    }else{
                        loadingAlert.dismiss()
                        canContinue = false
                    }
                }
            }
        }
        timer.start()
    }

    private fun alertDialogOpener(dialogLayout: View, context: Context): AlertDialog {
        var alertDialog: AlertDialog? = null
        val alertDialogBuilder = AlertDialog.Builder(context)

        alertDialogBuilder.setView(dialogLayout)
        alertDialog = alertDialogBuilder.create()

        return alertDialog
    }

}