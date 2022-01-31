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
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.elektra.ektp.R
import com.elektra.ektp.databinding.FragmentCreateAccountBinding
import com.elektra.ektp.ektpcreateaccount.viewmodel.EKTPCreateAccountViewModel
import java.util.*

class EKTPCreateAccountFragment : Fragment() {

    private lateinit var binding: FragmentCreateAccountBinding
    private val createAccountViewModel: EKTPCreateAccountViewModel by viewModels()

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentCreateAccountBinding>(inflater,
            R.layout.fragment_create_account, container, false)

        binding.insertName.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                name = s.toString()
                if (createAccountViewModel.checkValidInput(name)){
                    binding.insertName.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    binding.invalidNameText.isVisible = false
                }
                else{
                    binding.insertName.setBackgroundResource(R.drawable.validation_edit_text)
                    binding.button.isEnabled = false
                    binding.invalidNameText.isVisible = true
                }
            }

            override fun afterTextChanged(s: Editable?) {
                name = s.toString()
                if (createAccountViewModel.checkValidInput(name)){
                    binding.insertName.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    binding.button.isEnabled = createAccountViewModel.checkFilledFields(
                        name, paternalLast, birthDate, birthState,
                        phone, eMailText, emailConfirmationText, gender
                    )
                    binding.invalidNameText.isVisible = false
                }
                else{
                    binding.insertName.setBackgroundResource(R.drawable.validation_edit_text)
                    binding.button.isEnabled = createAccountViewModel.checkFilledFields(
                        name, paternalLast, birthDate, birthState,
                        phone, eMailText, emailConfirmationText, gender
                    )
                    binding.invalidNameText.isVisible = true
                }
            }

        })

        binding.paternalLastName.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                paternalLast = s.toString()
                if (createAccountViewModel.checkValidInput(paternalLast)){
                    binding.paternalLastName.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    binding.invalidPaternalText.isVisible = false
                }
                else{
                    binding.paternalLastName.setBackgroundResource(R.drawable.validation_edit_text)
                    binding.button.isEnabled = false
                    binding.invalidPaternalText.isVisible = true
                }
            }

            override fun afterTextChanged(s: Editable?) {
                paternalLast = s.toString()
                if (createAccountViewModel.checkValidInput(paternalLast)){
                    binding.paternalLastName.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    binding.button.isEnabled = createAccountViewModel.checkFilledFields(
                        name, paternalLast, birthDate, birthState,
                        phone, eMailText, emailConfirmationText, gender
                    )
                    binding.invalidPaternalText.isVisible = false
                }
                else{
                    binding.paternalLastName.setBackgroundResource(R.drawable.validation_edit_text)
                    binding.button.isEnabled = createAccountViewModel.checkFilledFields(
                        name, paternalLast, birthDate, birthState,
                        phone, eMailText, emailConfirmationText, gender
                    )
                    binding.invalidPaternalText.isVisible = true
                }
            }

        })

        binding.maternalLastName.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                maternalLast = s.toString()
                if (createAccountViewModel.checkValidInput(maternalLast) || maternalLast.isEmpty()){
                    binding.maternalLastName.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    binding.invalidMaternalText.isVisible = false
                }
                else{
                    binding.maternalLastName.setBackgroundResource(R.drawable.validation_edit_text)
                    binding.invalidMaternalText.isVisible = true
                }
            }

            override fun afterTextChanged(s: Editable?) {
                maternalLast = s.toString()
                if (createAccountViewModel.checkValidInput(maternalLast) || maternalLast.isEmpty()){
                    binding.maternalLastName.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    binding.invalidMaternalText.isVisible = false
                }
                else{
                    binding.maternalLastName.setBackgroundResource(R.drawable.validation_edit_text)
                    binding.invalidMaternalText.isVisible = true
                }
            }

        })

        binding.dateBirth.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                birthDate = s.toString()
                if (createAccountViewModel.checkValidDate(birthDate)){
                    binding.dateBirth.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    binding.invalidDateText.isVisible = false
                }
                else{
                    binding.dateBirth.setBackgroundResource(R.drawable.validation_edit_text)
                    binding.button.isEnabled = false
                    binding.invalidDateText.isVisible = true
                }
            }

            override fun afterTextChanged(s: Editable?) {
                birthDate = s.toString()
                if (createAccountViewModel.checkValidDate(birthDate)){
                    binding.dateBirth.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    binding.button.isEnabled = createAccountViewModel.checkFilledFields(
                        name, paternalLast, birthDate, birthState,
                        phone, eMailText, emailConfirmationText, gender
                    )
                    binding.invalidDateText.isVisible = false
                }
                else{
                    binding.dateBirth.setBackgroundResource(R.drawable.validation_edit_text)
                    binding.button.isEnabled = createAccountViewModel.checkFilledFields(
                        name, paternalLast, birthDate, birthState,
                        phone, eMailText, emailConfirmationText, gender
                    )
                    binding.invalidDateText.isVisible = true
                }
            }

        })

        binding.phoneNumber.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                phone = s.toString()
                if(createAccountViewModel.checkPhoneNumber(phone)) {
                    binding.phoneNumber.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    binding.invalidPhoneText.isVisible = false
                }
                else{
                    binding.phoneNumber.setBackgroundResource(R.drawable.validation_edit_text)
                    binding.invalidPhoneText.isVisible = true
                }
            }

            override fun afterTextChanged(s: Editable?) {
                phone = s.toString()
                if(createAccountViewModel.checkPhoneNumber(phone)) {
                    binding.phoneNumber.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    binding.button.isEnabled = createAccountViewModel.checkFilledFields(
                        name, paternalLast, birthDate, birthState,
                        phone, eMailText, emailConfirmationText, gender
                    )
                    binding.invalidPhoneText.isVisible = false
                }
                else{
                    binding.phoneNumber.setBackgroundResource(R.drawable.validation_edit_text)
                    binding.button.isEnabled = createAccountViewModel.checkFilledFields(
                        name, paternalLast, birthDate, birthState,
                        phone, eMailText, emailConfirmationText, gender
                    )
                    binding.invalidPhoneText.isVisible = true
                }
            }
        })

        binding.setDateButton.setOnClickListener {
            val dpp = DatePickerDialog(
                requireContext(),
                DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDay ->
                    binding.dateBirth.setText("" + mDay + "/" + "${mMonth + 1}" + "/" + mYear)
                },
                year,
                month,
                day
            )
            dpp.show()
        }

        binding.eMail.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                eMailText = s.toString()
                if (createAccountViewModel.checkEmail(eMailText)){
                    binding.eMail.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    binding.invalidEmailText.isVisible = false
                }
                else{
                    binding.eMail.setBackgroundResource(R.drawable.validation_edit_text)
                    binding.button.isEnabled = false
                    binding.invalidEmailText.isVisible = true
                }
            }

            override fun afterTextChanged(s: Editable?) {
                eMailText = s.toString()
                if (!createAccountViewModel.checkEmail(eMailText)){
                    binding.eMail.setBackgroundResource(R.drawable.validation_edit_text)
                    binding.button.isEnabled = createAccountViewModel.checkFilledFields(
                        name, paternalLast, birthDate, birthState,
                        phone, eMailText, emailConfirmationText, gender
                    )
                    binding.invalidEmailText.isVisible = true
                }
                else{
                    binding.eMail.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    binding.button.isEnabled = createAccountViewModel.checkFilledFields(
                        name, paternalLast, birthDate, birthState,
                        phone, eMailText, emailConfirmationText, gender
                    )
                    binding.invalidEmailText.isVisible = false
                }
            }
        })

        binding.emailConfirmation.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                emailConfirmationText = s.toString()
                if (eMailText != emailConfirmationText){
                    binding.emailConfirmation.setBackgroundResource(R.drawable.validation_edit_text)
                    binding.button.isEnabled = createAccountViewModel.checkFilledFields(
                        name, paternalLast, birthDate, birthState,
                        phone, eMailText, emailConfirmationText, gender
                    )
                    binding.invalidEmailConfirmationText.isVisible = true
                }
                else{
                    binding.emailConfirmation.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    binding.button.isEnabled = createAccountViewModel.checkFilledFields(
                        name, paternalLast, birthDate, birthState,
                        phone, eMailText, emailConfirmationText, gender
                    )
                    binding.invalidEmailConfirmationText.isVisible = false
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                emailConfirmationText = s.toString()
                if (eMailText != emailConfirmationText){
                    binding.emailConfirmation.setBackgroundResource(R.drawable.validation_edit_text)
                    binding.button.isEnabled = false
                    binding.invalidEmailConfirmationText.isVisible = true
                }
                else{
                    binding.emailConfirmation.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    binding.button.isEnabled = createAccountViewModel.checkFilledFields(
                        name, paternalLast, birthDate, birthState,
                        phone, eMailText, emailConfirmationText, gender
                    )
                    binding.invalidEmailConfirmationText.isVisible = false
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

        binding.birthSiteSpinner?.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    birthState = binding.birthSiteSpinner.selectedItem.toString()
                    binding.button.isEnabled = createAccountViewModel.checkFilledFields(
                        name, paternalLast, birthDate, birthState,
                        phone, eMailText, emailConfirmationText, gender
                    )
                }
            }

        binding.womanGenderRadioButton.setOnClickListener {
            gender = "Mujer"
            binding.button.isEnabled = createAccountViewModel.checkFilledFields(
                name, paternalLast, birthDate, birthState,
                phone, eMailText, emailConfirmationText, gender
            )
            binding.manGenderRadioButton.isChecked = false
        }

        binding.manGenderRadioButton.setOnClickListener {
            gender = "Hombre"
            binding.button.isEnabled = createAccountViewModel.checkFilledFields(
                name, paternalLast, birthDate, birthState,
                phone, eMailText, emailConfirmationText, gender
            )
            binding.womanGenderRadioButton.isChecked = false
        }

        return binding.root
    }

}