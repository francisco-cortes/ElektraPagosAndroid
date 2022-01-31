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
import com.elektra.ektp.databinding.FragmentEkptCreateAccountRegisterFormBinding
import com.elektra.ektp.ektpcreateaccount.viewmodel.EKTPCreateAccountRegisterFormViewModel
import com.elektra.ektp.ektpcreateaccount.viewmodel.EKTPCreateAccountSMSVerificationViewModel
import com.elektra.ektp.uservalidations.UserValidations

class EKPTCreateAccountRegisterFormFragment : Fragment() {

    //Access variables
    private lateinit var binding: FragmentEkptCreateAccountRegisterFormBinding
    private val registerFormViewModel: EKTPCreateAccountRegisterFormViewModel by viewModels()
    val validations = UserValidations()

    //Data variables
    private lateinit var userData: ArrayList<String>
    private var progressValue = 0
    private var completed: Int = 0
    private var zipCode: String = ""
    private var colonyUser: String = ""
    private var streetUser: String = ""
    private var exteriorNumber: String = ""
    private var interiorNumber: String = ""
    private var country: String = ""
    private var state: String = ""
    private var town: String = ""

    //Flags variables
    var zipCodeFlag = 0
    var colonyFlag = 0
    var streetFlag = 0
    var exteriorFlag = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_ekpt_create_account_register_form, container, false)
        progressInForm(progressValue, completed)

        userData = registerFormViewModel.getSavedRegisterData()


        binding.insertName.setText(userData[0])
        binding.paternalLastName.setText(userData[1])
        binding.maternalLastName.setText(userData[2])
        binding.dateBirth.setText(userData[3])
        binding.birthSiteSpinner.setText(userData[4])
        when (userData[5]) {
            "Hombre" -> {
                binding.manGenderRadioButton.isChecked = true
                binding.womanGenderRadioButton.isChecked = false
            }
            "Mujer" -> {
                binding.manGenderRadioButton.isChecked = false
                binding.womanGenderRadioButton.isChecked = true
            }
        }

        binding.postalCode.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                zipCode = s.toString()
                if (validations.checkZipCode(zipCode)) {
                    binding.postalCode.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    binding.button5.isEnabled =
                        validations.checkFieldsProgressBar(
                            zipCode,
                            colonyUser,
                            streetUser,
                            exteriorNumber,
                            country,
                            state,
                            town,
                            completed
                        )
                    binding.invalidZipCodeText.isVisible = false
                } else {
                    binding.postalCode.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    binding.button5.isEnabled = false
                    binding.invalidZipCodeText.isVisible = true
                }
            }

            override fun afterTextChanged(s: Editable?) {
                zipCode = s.toString()
                if (validations.checkZipCode(zipCode)) {
                    binding.postalCode.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    binding.button5.isEnabled =
                        validations.checkFieldsProgressBar(
                            zipCode,
                            colonyUser,
                            streetUser,
                            exteriorNumber,
                            country,
                            state,
                            town,
                            completed
                        )
                    binding.invalidZipCodeText.isVisible = false
                } else {
                    binding.postalCode.setBackgroundResource(R.drawable.validation_edit_text)
                    binding.button5.isEnabled =
                        validations.checkFieldsProgressBar(
                            zipCode,
                            colonyUser,
                            streetUser,
                            exteriorNumber,
                            country,
                            state,
                            town,
                            completed
                        )
                    binding.invalidZipCodeText.isVisible = true
                }
            }

        })

        binding.postalCode.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                if (validations.checkZipCode(zipCode) && zipCodeFlag == 0) {
                    zipCodeFlag = 1
                    progressValue += 10
                    completed += 1
                    binding.button5.isEnabled =
                        validations.checkFieldsProgressBar(
                            zipCode,
                            colonyUser,
                            streetUser,
                            exteriorNumber,
                            country,
                            state,
                            town,
                            completed
                        )
                } else {
                    if (!validations.checkZipCode(zipCode) && zipCodeFlag == 1) {
                        zipCodeFlag = 0
                        progressValue -= 10
                        completed -= 1
                    }
                }
                progressInForm(progressValue, completed)
            }
        }

        binding.insertColony.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                colonyUser = s.toString()
                if (validations.checkValidInput(colonyUser)) {
                    binding.insertColony.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    binding.button5.isEnabled =
                        validations.checkFieldsProgressBar(
                            zipCode,
                            colonyUser,
                            streetUser,
                            exteriorNumber,
                            country,
                            state,
                            town,
                            completed
                        )
                    binding.invalidColonyText.isVisible = false
                } else {
                    binding.insertColony.setBackgroundResource(R.drawable.validation_edit_text)
                    binding.button5.isEnabled = false
                    binding.invalidColonyText.isVisible = true
                }
            }

            override fun afterTextChanged(s: Editable?) {
                colonyUser = s.toString()
                if (validations.checkValidInput(colonyUser)) {
                    binding.insertColony.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    binding.button5.isEnabled =
                        validations.checkFieldsProgressBar(
                            zipCode,
                            colonyUser,
                            streetUser,
                            exteriorNumber,
                            country,
                            state,
                            town,
                            completed
                        )
                    binding.invalidColonyText.isVisible = false
                } else {
                    binding.insertColony.setBackgroundResource(R.drawable.validation_edit_text)
                    binding.button5.isEnabled =
                        validations.checkFieldsProgressBar(
                            zipCode,
                            colonyUser,
                            streetUser,
                            exteriorNumber,
                            country,
                            state,
                            town,
                            completed
                        )
                    binding.invalidColonyText.isVisible = true
                }
            }

        })

        binding.insertColony.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                if (validations.checkValidInput(colonyUser) && colonyFlag == 0) {
                    colonyFlag = 1
                    progressValue += 10
                    completed += 1
                    binding.button5.isEnabled =
                        validations.checkFieldsProgressBar(
                            zipCode,
                            colonyUser,
                            streetUser,
                            exteriorNumber,
                            country,
                            state,
                            town,
                            completed
                        )
                } else {
                    if (!validations.checkValidInput(colonyUser) && colonyFlag == 1) {
                        colonyFlag = 0
                        progressValue -= 10
                        completed -= 1
                    }
                }
                progressInForm(progressValue, completed)
            }
        }

        binding.insertStreet.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                streetUser = s.toString()
                if (validations.checkValidInput(streetUser)){
                    binding.insertStreet.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    binding.button5.isEnabled =
                        validations.checkFieldsProgressBar(
                            zipCode,
                            colonyUser,
                            streetUser,
                            exteriorNumber,
                            country,
                            state,
                            town,
                            completed
                        )
                    binding.invalidStreetText.isVisible = false
                }
                else{
                    binding.insertStreet.setBackgroundResource(R.drawable.validation_edit_text)
                    binding.button5.isEnabled = false
                    binding.invalidStreetText.isVisible = true
                }
            }

            override fun afterTextChanged(s: Editable?) {
                streetUser = s.toString()
                if (validations.checkValidInput(streetUser)){
                    binding.insertStreet.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    binding.button5.isEnabled =
                        validations.checkFieldsProgressBar(
                            zipCode,
                            colonyUser,
                            streetUser,
                            exteriorNumber,
                            country,
                            state,
                            town,
                            completed
                        )
                    binding.invalidStreetText.isVisible = false
                }
                else{
                    binding.insertStreet.setBackgroundResource(R.drawable.validation_edit_text)
                    binding.button5.isEnabled =
                        validations.checkFieldsProgressBar(
                            zipCode,
                            colonyUser,
                            streetUser,
                            exteriorNumber,
                            country,
                            state,
                            town,
                            completed
                        )
                    binding.invalidStreetText.isVisible = true
                }
            }

        })

        binding.insertStreet.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                if (validations.checkValidInput(streetUser) && streetFlag == 0){
                    streetFlag = 1
                    progressValue += 10
                    completed += 1
                    binding.button5.isEnabled =
                        validations.checkFieldsProgressBar(
                            zipCode,
                            colonyUser,
                            streetUser,
                            exteriorNumber,
                            country,
                            state,
                            town,
                            completed
                        )
                }
                else{
                    if (!validations.checkValidInput(streetUser) && streetFlag ==1){
                        streetFlag = 0
                        progressValue -= 10
                        completed -= 1
                    }
                }
                progressInForm(progressValue, completed)
            }
        }

        binding.exteriorNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                exteriorNumber = s.toString()
                if (validations.checkValidInput(exteriorNumber)){
                    binding.exteriorNumber.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    binding.button5.isEnabled =
                        validations.checkFieldsProgressBar(
                            zipCode,
                            colonyUser,
                            streetUser,
                            exteriorNumber,
                            country,
                            state,
                            town,
                            completed
                        )
                    binding.invalidExteriorText.isVisible = false
                }
                else{
                    binding.exteriorNumber.setBackgroundResource(R.drawable.validation_edit_text)
                    binding.button5.isEnabled = false
                    binding.invalidExteriorText.isVisible = true
                }
            }

            override fun afterTextChanged(s: Editable?) {
                exteriorNumber = s.toString()
                if (validations.checkValidInput(exteriorNumber)){
                    binding.exteriorNumber.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    binding.button5.isEnabled =
                        validations.checkFieldsProgressBar(
                            zipCode,
                            colonyUser,
                            streetUser,
                            exteriorNumber,
                            country,
                            state,
                            town,
                            completed
                        )
                    binding.invalidExteriorText.isVisible = false
                }
                else{
                    binding.exteriorNumber.setBackgroundResource(R.drawable.validation_edit_text)
                    binding.button5.isEnabled =
                        validations.checkFieldsProgressBar(
                            zipCode,
                            colonyUser,
                            streetUser,
                            exteriorNumber,
                            country,
                            state,
                            town,
                            completed
                        )
                    binding.invalidExteriorText.isVisible = true
                }
            }

        })

        binding.exteriorNumber.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                if (validations.checkValidInput(exteriorNumber) && exteriorFlag == 0){
                    exteriorFlag = 1
                    progressValue += 10
                    completed += 1
                    binding.button5.isEnabled =
                        validations.checkFieldsProgressBar(
                            zipCode,
                            colonyUser,
                            streetUser,
                            exteriorNumber,
                            country,
                            state,
                            town,
                            completed
                        )
                }
                else{
                    if (!validations.checkValidInput(exteriorNumber) && exteriorFlag ==1){
                        exteriorFlag = 0
                        progressValue -= 10
                        completed -= 1
                    }
                }
                progressInForm(progressValue, completed)
            }
        }


        return binding.root
    }

    private fun progressInForm(proValBar: Int, proTexVal: Int) {
        binding.progressBar.progress = proValBar
        if (proTexVal <= 7) {
            binding.barCounter.text = "$proTexVal/7"
        }
    }

}