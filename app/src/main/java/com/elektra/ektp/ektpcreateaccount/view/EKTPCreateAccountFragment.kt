package com.elektra.ektp.ektpcreateaccount.view

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.elektra.ektp.R
import com.elektra.ektp.databinding.FragmentCreateAccountBinding
import com.elektra.ektp.ektpcreateaccount.viewmodel.EKTPCreateAccountViewModel
import com.elektra.ektp.ektplogin.view.EKTPLoginActivity
import com.elektra.ektp.ektpsharedpreferences.EKTPUserApplication.Companion.preferences
import com.elektra.ektp.ektputilies.uservalidations.UserValidations
import kotlinx.coroutines.Job
import java.util.*

class EKTPCreateAccountFragment : Fragment() {

    //Global variable for databinding
    private lateinit var binding: FragmentCreateAccountBinding
    private lateinit var loadingLayout: View
    //---
    //ViewModel variable access
    private val createAccountViewModel: EKTPCreateAccountViewModel by viewModels()
    //---
    //SharedPreferences variable access
    val validations = UserValidations()
    //---
    //Global variables for user entries
    private var name: String = ""
    private var paternalLast: String = ""
    private var maternalLast: String = ""
    private var birthDate: String = ""
    private var apiBday = ""
    private var birthState: String = ""
    private var gender: String = ""
    private var phone: String = "Selecciona una opción*"
    private var eMailText: String = ""
    private var emailConfirmationText: String = ""
    private val c = Calendar.getInstance()
    private val year = c.get(Calendar.YEAR)-18
    private val month = c.get(Calendar.MONTH)
    private val day = c.get(Calendar.DAY_OF_MONTH)
    private var canContinue = false
    //---

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Overriding OnBackPressed function to destroy fragment and activity
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                isEnabled = false
                activity?.onBackPressed()
                val intent = Intent(activity, EKTPLoginActivity::class.java)
                val context = view?.context
                context?.startActivity(intent)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_create_account, container, false)
        loadingLayout = layoutInflater.inflate(R.layout.loading_alert_layout,container,false)

        //Wrap this block code for all the lines with binding variable
        with(binding){
            //Initialize disable button
            button.isEnabled = false

            //TextWatcher function to listen for changes on editText
            insertName.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    name = s.toString()
                    if (validations.checkValidInput(name)){
                        insertName.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                        invalidNameText.isVisible = false
                    }
                    else{
                        insertName.setBackgroundResource(R.drawable.validation_edit_text)
                        button.isEnabled = false
                        invalidNameText.isVisible = true
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    name = s.toString()
                    if (validations.checkValidInput(name)){
                        insertName.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                        button.isEnabled = validations.checkFilledFields(
                            name, paternalLast, birthDate, birthState,
                            phone, eMailText, emailConfirmationText, gender
                        )
                        invalidNameText.isVisible = false
                    }
                    else{
                        insertName.setBackgroundResource(R.drawable.validation_edit_text)
                        button.isEnabled = validations.checkFilledFields(
                            name, paternalLast, birthDate, birthState,
                            phone, eMailText, emailConfirmationText, gender
                        )
                        invalidNameText.isVisible = true
                    }
                }

            })
            //---
            //TextWatcher function to listen for changes on editText
            paternalLastName.addTextChangedListener(object: TextWatcher{
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    paternalLast = s.toString()
                    if (validations.checkValidInput(paternalLast)){
                        paternalLastName.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                        invalidPaternalText.isVisible = false
                    }
                    else{
                        paternalLastName.setBackgroundResource(R.drawable.validation_edit_text)
                        button.isEnabled = false
                        invalidPaternalText.isVisible = true
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    paternalLast = s.toString()
                    if (validations.checkValidInput(paternalLast)){
                        paternalLastName.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                        button.isEnabled = validations.checkFilledFields(
                            name, paternalLast, birthDate, birthState,
                            phone, eMailText, emailConfirmationText, gender
                        )
                        invalidPaternalText.isVisible = false
                    }
                    else{
                        paternalLastName.setBackgroundResource(R.drawable.validation_edit_text)
                        button.isEnabled = validations.checkFilledFields(
                            name, paternalLast, birthDate, birthState,
                            phone, eMailText, emailConfirmationText, gender
                        )
                        invalidPaternalText.isVisible = true
                    }
                }

            })
            //---
            //TextWatcher function to listen for changes on editText
            maternalLastName.addTextChangedListener(object: TextWatcher{
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    maternalLast = s.toString()
                    if (validations.checkValidInput(maternalLast) || maternalLast.isEmpty()){
                        maternalLastName.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                        invalidMaternalText.isVisible = false
                    }
                    else{
                        maternalLastName.setBackgroundResource(R.drawable.validation_edit_text)
                        invalidMaternalText.isVisible = true
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    maternalLast = s.toString()
                    if (validations.checkValidInput(maternalLast) || maternalLast.isEmpty()){
                        maternalLastName.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                        invalidMaternalText.isVisible = false
                    }
                    else{
                        maternalLastName.setBackgroundResource(R.drawable.validation_edit_text)
                        invalidMaternalText.isVisible = true
                    }
                }

            })
            //---
            //TextWatcher function to listen for changes on editText
            dateBirth.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    birthDate = s.toString()
                    if (validations.checkValidDate(birthDate)){
                        dateBirth.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                        invalidDateText.isVisible = false
                    }
                    else{
                        dateBirth.setBackgroundResource(R.drawable.validation_edit_text)
                        button.isEnabled = false
                        invalidDateText.isVisible = true
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    birthDate = s.toString()
                    if (validations.checkValidDate(birthDate)){
                        dateBirth.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                        button.isEnabled = validations.checkFilledFields(
                            name, paternalLast, birthDate, birthState,
                            phone, eMailText, emailConfirmationText, gender
                        )
                        invalidDateText.isVisible = false
                    }
                    else{
                        dateBirth.setBackgroundResource(R.drawable.validation_edit_text)
                        button.isEnabled = validations.checkFilledFields(
                            name, paternalLast, birthDate, birthState,
                            phone, eMailText, emailConfirmationText, gender
                        )
                        invalidDateText.isVisible = true
                    }
                }

            })
            //---
            //TextWatcher function to listen for changes on editText
            phoneNumber.addTextChangedListener(object: TextWatcher{
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    phone = s.toString()
                    if(validations.checkPhoneNumber(phone)) {
                        phoneNumber.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                        invalidPhoneText.isVisible = false
                    }
                    else{
                        phoneNumber.setBackgroundResource(R.drawable.validation_edit_text)
                        invalidPhoneText.isVisible = true
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    phone = s.toString()
                    if(validations.checkPhoneNumber(phone)) {
                        phoneNumber.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                        button.isEnabled = validations.checkFilledFields(
                            name, paternalLast, birthDate, birthState,
                            phone, eMailText, emailConfirmationText, gender
                        )
                        invalidPhoneText.isVisible = false
                    }
                    else{
                        phoneNumber.setBackgroundResource(R.drawable.validation_edit_text)
                        button.isEnabled = validations.checkFilledFields(
                            name, paternalLast, birthDate, birthState,
                            phone, eMailText, emailConfirmationText, gender
                        )
                        invalidPhoneText.isVisible = true
                    }
                }
            })
            //---
            //TextWatcher function to listen for changes on editText
            setDateButton.setOnClickListener {
                val dpp = DatePickerDialog(
                    requireContext(), { _, mYear, mMonth, mDay ->
                        val sMonth = if(mMonth < 10){
                            "0${mMonth+1}"
                        } else{
                            "${mMonth+1}"
                        }
                        val sDay = if(mDay < 10){
                            "0$mDay"
                        } else{
                            "$mDay"
                        }
                        val birthday = "$sDay/$sMonth/$mYear"
                        apiBday = "$mYear-$sMonth-$sDay"
                        dateBirth.setText(birthday)
                    },
                    year,
                    month,
                    day
                )
                c.set(year,month,day)
                dpp.datePicker.maxDate = c.timeInMillis
                dpp.show()
            }
            //--
            //TextWatcher function to listen for changes on editText
            eMail.addTextChangedListener(object: TextWatcher{
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    eMailText = s.toString()
                    if (validations.checkEmail(eMailText)){
                        eMail.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                        invalidEmailText.isVisible = false
                    }
                    else{
                        eMail.setBackgroundResource(R.drawable.validation_edit_text)
                        button.isEnabled = false
                        invalidEmailText.isVisible = true
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    eMailText = s.toString()
                    if (!validations.checkEmail(eMailText)){
                        eMail.setBackgroundResource(R.drawable.validation_edit_text)
                        button.isEnabled = validations.checkFilledFields(
                            name, paternalLast, birthDate, birthState,
                            phone, eMailText, emailConfirmationText, gender
                        )
                        invalidEmailText.isVisible = true
                        invalidEmailConfirmationText.isVisible = false
                    }
                    else{
                        invalidEmailText.isVisible = false
                        eMail.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                        if (eMailText != emailConfirmationText && emailConfirmationText.isNotEmpty()){
                            emailConfirmation.setBackgroundResource(R.drawable.validation_edit_text)
                            button.isEnabled = validations.checkFilledFields(
                                name, paternalLast, birthDate, birthState,
                                phone, eMailText, emailConfirmationText, gender
                            )
                            invalidEmailConfirmationText.isVisible = true
                        }
                        else{
                            emailConfirmation.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                            button.isEnabled = validations.checkFilledFields(
                                name, paternalLast, birthDate, birthState,
                                phone, eMailText, emailConfirmationText, gender
                            )
                            invalidEmailConfirmationText.isVisible = false
                        }
                    }
                }
            })
            //
            //TextWatcher function to listen for changes on editText
            emailConfirmation.addTextChangedListener(object: TextWatcher{
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    emailConfirmationText = s.toString()
                    if (eMailText != emailConfirmationText){
                        emailConfirmation.setBackgroundResource(R.drawable.validation_edit_text)
                        button.isEnabled = validations.checkFilledFields(
                            name, paternalLast, birthDate, birthState,
                            phone, eMailText, emailConfirmationText, gender
                        )
                        invalidEmailConfirmationText.isVisible = true
                    }
                    else{
                        emailConfirmation.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                        button.isEnabled = validations.checkFilledFields(
                            name, paternalLast, birthDate, birthState,
                            phone, eMailText, emailConfirmationText, gender
                        )
                        invalidEmailConfirmationText.isVisible = false
                    }
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    emailConfirmationText = s.toString()
                    if (eMailText != emailConfirmationText){
                        emailConfirmation.setBackgroundResource(R.drawable.validation_edit_text)
                        button.isEnabled = false
                        invalidEmailConfirmationText.isVisible = true
                    }
                    else{
                        emailConfirmation.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                        button.isEnabled = validations.checkFilledFields(
                            name, paternalLast, birthDate, birthState,
                            phone, eMailText, emailConfirmationText, gender
                        )
                        invalidEmailConfirmationText.isVisible = false
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    emailConfirmationText = s.toString()
                    if (eMailText != emailConfirmationText){
                        emailConfirmation.setBackgroundResource(R.drawable.validation_edit_text)
                        button.isEnabled = false
                        invalidEmailConfirmationText.isVisible = true
                    }
                    else{
                        emailConfirmation.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                        button.isEnabled = validations.checkFilledFields(
                            name, paternalLast, birthDate, birthState,
                            phone, eMailText, emailConfirmationText, gender
                        )
                        invalidEmailConfirmationText.isVisible = false
                    }
                }
            })
            //---
            //OnItemSelectedListener function on Spinner to listen for selection changes
            birthSiteSpinner.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }

                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long,
                    ) {
                        birthSiteSpinner.isFocusableInTouchMode = true
                        birthSiteSpinner.requestFocus()
                        birthSiteSpinner.isFocusableInTouchMode = false
                        birthSiteSpinner.clearFocus()
                        birthState = birthSiteSpinner.selectedItem.toString()
                        button.isEnabled = validations.checkFilledFields(
                            name, paternalLast, birthDate, birthState,
                            phone, eMailText, emailConfirmationText, gender
                        )
                    }
                }
            //---
            //onClickListener on WomanGender to listen for radioButton selection
            womanGenderRadioButton.setOnClickListener {
                gender = "M"
                button.isEnabled = validations.checkFilledFields(
                    name, paternalLast, birthDate, birthState,
                    phone, eMailText, emailConfirmationText, gender
                )
                manGenderRadioButton.isChecked = false
            }
            //---
            //onClickListener on ManGender to listen for radioButton selection
            manGenderRadioButton.setOnClickListener {
                gender = "H"
                button.isEnabled = validations.checkFilledFields(
                    name, paternalLast, birthDate, birthState,
                    phone, eMailText, emailConfirmationText, gender
                )
                womanGenderRadioButton.isChecked = false
            }
            //---
            //onClickListener on continueButton to listen for saveUserData in SharedPreferences
            button.setOnClickListener {
                createAccountViewModel.saveRegisterData(
                    name, paternalLast, maternalLast, birthDate, birthState, phone, eMailText, gender,apiBday
                )
                preferences.saveLocalStatus("preRegistrado")
                //val value = createAccountViewModel.apiFolioValClientes(phone,name,paternalLast,maternalLast,apiBday,gender,eMailText,birthState,"")
                //verifyFoliValClientResponse(value)
                fragmentReplacer(EKTPCreateAccountSMSVerificationFragment())
            }
            //---
            //onClickListener on appBar BackButton to destroy fragment and activity
            backAppbarButton.setOnClickListener { view: View ->
                val intent = Intent(activity, EKTPLoginActivity::class.java)
                val context = view.context
                context?.startActivity(intent)
                activity?.finish()
            }
            //---
            return root
        }
        //---
    }

    private fun verifyFoliValClientResponse(value: Job) {
        val loadingAlert = alertDialogOpener(loadingLayout, requireContext())
        loadingAlert.show()
        loadingAlert.window?.setLayout(250, 250)
        var attempts: Int
        val timer = object : CountDownTimer(7000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                if(value.isCompleted){
                    if (createAccountViewModel.canContinue){
                        loadingAlert.dismiss()
                        fragmentReplacer(EKTPCreateAccountSMSVerificationFragment())
                        //view?.findNavController()?.navigate(R.id.action_EKTPCreateAccountFragment_to_EKTPCreateAccountSMSVerificationFragment)
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
                    if (createAccountViewModel.canContinue){
                        loadingAlert.dismiss()
                        fragmentReplacer(EKTPCreateAccountSMSVerificationFragment())
                        //view?.findNavController()?.navigate(R.id.action_EKTPCreateAccountFragment_to_EKTPCreateAccountSMSVerificationFragment)
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
        val alertDialog: AlertDialog?
        val alertDialogBuilder = AlertDialog.Builder(context)

        alertDialogBuilder.setView(dialogLayout)
        alertDialog = alertDialogBuilder.create()

        return alertDialog
    }

    private fun fragmentReplacer(fragment: Fragment){
        requireActivity()
            .supportFragmentManager
            .beginTransaction()
            .replace(R.id.CreateAccountNavigatorHost,fragment)
            .commitNow()//open the biometric login fragment
    }

}