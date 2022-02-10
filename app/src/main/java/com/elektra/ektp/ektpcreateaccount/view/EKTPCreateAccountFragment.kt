package com.elektra.ektp.ektpcreateaccount.view

import android.app.DatePickerDialog
import android.os.Bundle
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
import androidx.navigation.findNavController
import com.elektra.ektp.R
import com.elektra.ektp.databinding.FragmentCreateAccountBinding
import com.elektra.ektp.ektpcreateaccount.viewmodel.EKTPCreateAccountViewModel
import com.elektra.ektp.uservalidations.UserValidations
import java.util.*

class EKTPCreateAccountFragment : Fragment() {

    //Global variable for databinding
    private lateinit var binding: FragmentCreateAccountBinding
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
    private var birthState: String = ""
    private var gender: String = ""
    private var phone: String = "Selecciona una opción*"
    private var eMailText: String = ""
    private var emailConfirmationText: String = ""
    val c = Calendar.getInstance()
    val year = c.get(Calendar.YEAR)
    val month = c.get(Calendar.MONTH)
    val day = c.get(Calendar.DAY_OF_MONTH)
    //---

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Overriding OnBackPressed function to destroy fragment and activity
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                isEnabled = false
                activity?.onBackPressed()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentCreateAccountBinding>(inflater,
            R.layout.fragment_create_account, container, false)

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
                    requireContext(),
                    DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDay ->
                        dateBirth.setText("" + mDay + "/" + "${mMonth + 1}" + "/" + mYear)
                    },
                    year,
                    month,
                    day
                )
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
                    }
                    else{
                        eMail.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                        button.isEnabled = validations.checkFilledFields(
                            name, paternalLast, birthDate, birthState,
                            phone, eMailText, emailConfirmationText, gender
                        )
                        invalidEmailText.isVisible = false
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
                        id: Long
                    ) {
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
                gender = "Mujer"
                button.isEnabled = validations.checkFilledFields(
                    name, paternalLast, birthDate, birthState,
                    phone, eMailText, emailConfirmationText, gender
                )
                manGenderRadioButton.isChecked = false
            }
            //---
            //onClickListener on ManGender to listen for radioButton selection
            manGenderRadioButton.setOnClickListener {
                gender = "Hombre"
                button.isEnabled = validations.checkFilledFields(
                    name, paternalLast, birthDate, birthState,
                    phone, eMailText, emailConfirmationText, gender
                )
                womanGenderRadioButton.isChecked = false
            }
            //---
            //onClickListener on continueButton to listen for saveUserData in SharedPreferences
            button.setOnClickListener { view: View ->
                createAccountViewModel.saveRegisterData(
                    name, paternalLast, maternalLast, birthDate, birthState, phone, eMailText, gender
                )
                view.findNavController().navigate(R.id.action_EKTPCreateAccountFragment_to_EKTPCreateAccountSMSVerificationFragment)
            }
            //---
            //onClickListener on appBar BackButton to destroy fragment and activity
            backAppbarButton.setOnClickListener { view: View ->
                activity?.finish()
            }
            //---
            return root
        }
        //---
    }
}