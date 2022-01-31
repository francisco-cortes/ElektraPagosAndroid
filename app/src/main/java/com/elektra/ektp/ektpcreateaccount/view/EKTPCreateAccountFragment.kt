package com.elektra.ektp.ektpcreateaccount.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        return binding.root
    }

}