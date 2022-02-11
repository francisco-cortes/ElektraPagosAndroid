package com.elektra.ektp.ektpcreateaccount.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.elektra.ektp.R
import com.elektra.ektp.databinding.FragmentEkptCreateAccountRegisterFormBinding
import com.elektra.ektp.ektpcreateaccount.viewmodel.EKTPCreateAccountRegisterFormViewModel
import com.elektra.ektp.ektpcreateaccount.viewmodel.EKTPCreateAccountSMSVerificationViewModel
import com.elektra.ektp.uservalidations.UserValidations

class EKPTCreateAccountRegisterFormFragment : Fragment() {

    //Access variables
    private lateinit var binding: FragmentEkptCreateAccountRegisterFormBinding
    private val registerFormViewModel: EKTPCreateAccountRegisterFormViewModel by viewModels()
    private val validations = UserValidations()
    //---

    //Data variables
    private lateinit var userData: ArrayList<String>
    private var progressValue = 0
    private var completed: Int = 0
    private var zipCode: String = ""
    private var colonyUser: String = ""
    private var streetUser: String = ""
    private var exteriorNumberString: String = ""
    private var interiorNumberString: String = ""
    private var country: String = ""
    private var state: String = ""
    private var town: String = ""
    //---

    //Flags variables
    var zipCodeFlag = 0
    var colonyFlag = 0
    var streetFlag = 0
    var exteriorFlag = 0
    //---

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
            R.layout.fragment_ekpt_create_account_register_form, container, false)

        progressInForm(progressValue, completed)

        userData = registerFormViewModel.getSavedRegisterData()

        //Wrap this block code for all the lines with binding variable
        with(binding){
            //Recover data from SharedPreferences and initialize fields
            insertName.setText(userData[0])
            paternalLastName.setText(userData[1])
            maternalLastName.setText(userData[2])
            dateBirth.setText(userData[3])
            birthSiteSpinner.setText(userData[4])
            when (userData[5]) {
                "Hombre" -> {
                    manGenderRadioButton.isChecked = true
                    womanGenderRadioButton.isChecked = false
                }
                "Mujer" -> {
                    manGenderRadioButton.isChecked = false
                    womanGenderRadioButton.isChecked = true
                }
            }
            //---

            //TextWatcher function to listen for changes on editText
            postalCode.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    zipCode = s.toString()
                    if (validations.checkZipCode(zipCode)) {
                        postalCode.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                        button5.isEnabled =
                            validations.checkFieldsProgressBar(
                                zipCode,
                                colonyUser,
                                streetUser,
                                exteriorNumberString,
                                country,
                                state,
                                town,
                                completed
                            )
                        invalidZipCodeText.isVisible = false
                    } else {
                        postalCode.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                        button5.isEnabled = false
                        invalidZipCodeText.isVisible = true
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    zipCode = s.toString()
                    if (validations.checkZipCode(zipCode)) {
                        postalCode.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                        button5.isEnabled =
                            validations.checkFieldsProgressBar(
                                zipCode,
                                colonyUser,
                                streetUser,
                                exteriorNumberString,
                                country,
                                state,
                                town,
                                completed
                            )
                        invalidZipCodeText.isVisible = false
                    } else {
                        postalCode.setBackgroundResource(R.drawable.validation_edit_text)
                        button5.isEnabled =
                            validations.checkFieldsProgressBar(
                                zipCode,
                                colonyUser,
                                streetUser,
                                exteriorNumberString,
                                country,
                                state,
                                town,
                                completed
                            )
                        invalidZipCodeText.isVisible = true
                    }
                }

            })
            //--

            //OnFocusChangeListener function to listen for changes on editText unfocused
            postalCode.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                if (!hasFocus) {
                    if (validations.checkZipCode(zipCode) && zipCodeFlag == 0) {
                        zipCodeFlag = 1
                        progressValue += 10
                        completed += 1
                        button5.isEnabled =
                            validations.checkFieldsProgressBar(
                                zipCode,
                                colonyUser,
                                streetUser,
                                exteriorNumberString,
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
            //---

            //TextWatcher function to listen for changes on editText
            insertColony.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    colonyUser = s.toString()
                    if (validations.checkAddress(colonyUser)) {
                        insertColony.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                        button5.isEnabled =
                            validations.checkFieldsProgressBar(
                                zipCode,
                                colonyUser,
                                streetUser,
                                exteriorNumberString,
                                country,
                                state,
                                town,
                                completed
                            )
                        invalidColonyText.isVisible = false
                    } else {
                        insertColony.setBackgroundResource(R.drawable.validation_edit_text)
                        button5.isEnabled = false
                        invalidColonyText.isVisible = true
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    colonyUser = s.toString()
                    if (validations.checkAddress(colonyUser)) {
                        insertColony.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                        button5.isEnabled =
                            validations.checkFieldsProgressBar(
                                zipCode,
                                colonyUser,
                                streetUser,
                                exteriorNumberString,
                                country,
                                state,
                                town,
                                completed
                            )
                        invalidColonyText.isVisible = false
                    } else {
                        insertColony.setBackgroundResource(R.drawable.validation_edit_text)
                        button5.isEnabled =
                            validations.checkFieldsProgressBar(
                                zipCode,
                                colonyUser,
                                streetUser,
                                exteriorNumberString,
                                country,
                                state,
                                town,
                                completed
                            )
                        invalidColonyText.isVisible = true
                    }
                }

            })
            //---

            //OnFocusChangeListener function to listen for changes on editText unfocused
            insertColony.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                if (!hasFocus) {
                    if (validations.checkAddress(colonyUser) && colonyFlag == 0) {
                        colonyFlag = 1
                        progressValue += 10
                        completed += 1
                        button5.isEnabled =
                            validations.checkFieldsProgressBar(
                                zipCode,
                                colonyUser,
                                streetUser,
                                exteriorNumberString,
                                country,
                                state,
                                town,
                                completed
                            )
                    } else {
                        if (!validations.checkAddress(colonyUser) && colonyFlag == 1) {
                            colonyFlag = 0
                            progressValue -= 10
                            completed -= 1
                        }
                    }
                    progressInForm(progressValue, completed)
                }
            }
            //---

            //TextWatcher function to listen for changes on editText
            insertStreet.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    streetUser = s.toString()
                    if (validations.checkAddress(streetUser)){
                        insertStreet.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                        button5.isEnabled =
                            validations.checkFieldsProgressBar(
                                zipCode,
                                colonyUser,
                                streetUser,
                                exteriorNumberString,
                                country,
                                state,
                                town,
                                completed
                            )
                        invalidStreetText.isVisible = false
                    }
                    else{
                        insertStreet.setBackgroundResource(R.drawable.validation_edit_text)
                        button5.isEnabled = false
                        invalidStreetText.isVisible = true
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    streetUser = s.toString()
                    if (validations.checkAddress(streetUser)){
                        insertStreet.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                        button5.isEnabled =
                            validations.checkFieldsProgressBar(
                                zipCode,
                                colonyUser,
                                streetUser,
                                exteriorNumberString,
                                country,
                                state,
                                town,
                                completed
                            )
                        invalidStreetText.isVisible = false
                    }
                    else{
                        insertStreet.setBackgroundResource(R.drawable.validation_edit_text)
                        button5.isEnabled =
                            validations.checkFieldsProgressBar(
                                zipCode,
                                colonyUser,
                                streetUser,
                                exteriorNumberString,
                                country,
                                state,
                                town,
                                completed
                            )
                        invalidStreetText.isVisible = true
                    }
                }

            })
            //---

            //OnFocusChangeListener function to listen for changes on editText unfocused
            insertStreet.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                if (!hasFocus) {
                    if (validations.checkAddress(streetUser) && streetFlag == 0){
                        streetFlag = 1
                        progressValue += 10
                        completed += 1
                        button5.isEnabled =
                            validations.checkFieldsProgressBar(
                                zipCode,
                                colonyUser,
                                streetUser,
                                exteriorNumberString,
                                country,
                                state,
                                town,
                                completed
                            )
                    }
                    else{
                        if (!validations.checkAddress(streetUser) && streetFlag ==1){
                            streetFlag = 0
                            progressValue -= 10
                            completed -= 1
                        }
                    }
                    progressInForm(progressValue, completed)
                }
            }
            //---

            //TextWatcher function to listen for changes on editText
            exteriorNumber.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    exteriorNumberString = s.toString()
                    if (validations.checkAddress(exteriorNumberString)){
                        exteriorNumber.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                        button5.isEnabled =
                            validations.checkFieldsProgressBar(
                                zipCode,
                                colonyUser,
                                streetUser,
                                exteriorNumberString,
                                country,
                                state,
                                town,
                                completed
                            )
                        invalidExteriorText.isInvisible = true
                    }
                    else{
                        exteriorNumber.setBackgroundResource(R.drawable.validation_edit_text)
                        button5.isEnabled = false
                        invalidExteriorText.isInvisible = false
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    exteriorNumberString = s.toString()
                    if (validations.checkAddress(exteriorNumberString)){
                        exteriorNumber.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                        button5.isEnabled =
                            validations.checkFieldsProgressBar(
                                zipCode,
                                colonyUser,
                                streetUser,
                                exteriorNumberString,
                                country,
                                state,
                                town,
                                completed
                            )
                        invalidExteriorText.isInvisible = true
                    }
                    else{
                        exteriorNumber.setBackgroundResource(R.drawable.validation_edit_text)
                        button5.isEnabled =
                            validations.checkFieldsProgressBar(
                                zipCode,
                                colonyUser,
                                streetUser,
                                exteriorNumberString,
                                country,
                                state,
                                town,
                                completed
                            )
                        invalidExteriorText.isInvisible = false
                    }
                }

            })
            //---

            //OnFocusChangeListener function to listen for changes on editText unfocused
            exteriorNumber.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                if (!hasFocus) {
                    if (validations.checkAddress(exteriorNumberString) && exteriorFlag == 0){
                        exteriorFlag = 1
                        progressValue += 10
                        completed += 1
                        button5.isEnabled =
                            validations.checkFieldsProgressBar(
                                zipCode,
                                colonyUser,
                                streetUser,
                                exteriorNumberString,
                                country,
                                state,
                                town,
                                completed
                            )
                    }
                    else{
                        if (!validations.checkAddress(exteriorNumberString) && exteriorFlag ==1){
                            exteriorFlag = 0
                            progressValue -= 10
                            completed -= 1
                        }
                    }
                    progressInForm(progressValue, completed)
                }
            }
            //---

            //TextWatcher function to listen for changes on editText
            interiorNumber.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    interiorNumberString = s.toString()
                    if (validations.checkAddress(interiorNumberString) || interiorNumberString.isEmpty()){
                        interiorNumber.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                        invalidInteriorText.isInvisible = true
                    }
                    else{
                        interiorNumber.setBackgroundResource(R.drawable.validation_edit_text)
                        invalidInteriorText.isInvisible = false
                    }
                }

                override fun afterTextChanged(s: Editable?) {

                }

            })
            //---

            //OnItemSelectedListener function on Spinner to listen for selection changes
            countrySpinner?.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }

                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        if (country != "Selecciona una opción*" && country.isNotEmpty()) {
                            progressValue -= 10
                            completed -= 1
                            button5.isEnabled =
                                validations.checkFieldsProgressBar(
                                    zipCode,
                                    colonyUser,
                                    streetUser,
                                    exteriorNumberString,
                                    country,
                                    state,
                                    town,
                                    completed
                                )
                        }
                        country = countrySpinner.selectedItem.toString()
                        if (country != "Selecciona una opción*") {
                            progressValue += 10
                            completed += 1
                            button5.isEnabled =
                                validations.checkFieldsProgressBar(
                                    zipCode,
                                    colonyUser,
                                    streetUser,
                                    exteriorNumberString,
                                    country,
                                    state,
                                    town,
                                    completed
                                )
                        }
                        else{
                            button5.isEnabled = false
                        }
                        progressInForm(progressValue, completed)
                    }
                }
            //---

            //OnItemSelectedListener function on Spinner to listen for selection changes
            stateSpinner?.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }

                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        if (state != "Selecciona una opción*" && state.isNotEmpty()) {
                            progressValue -= 10
                            completed -= 1
                            button5.isEnabled =
                                validations.checkFieldsProgressBar(
                                    zipCode,
                                    colonyUser,
                                    streetUser,
                                    exteriorNumberString,
                                    country,
                                    state,
                                    town,
                                    completed
                                )
                        }
                        state = stateSpinner.selectedItem.toString()
                        if (state != "Selecciona una opción*") {
                            progressValue += 10
                            completed += 1
                            button5.isEnabled =
                                validations.checkFieldsProgressBar(
                                    zipCode,
                                    colonyUser,
                                    streetUser,
                                    exteriorNumberString,
                                    country,
                                    state,
                                    town,
                                    completed
                                )
                        }
                        else{
                            button5.isEnabled = false
                        }
                        progressInForm(progressValue, completed)
                    }
                }
            //---

            //OnItemSelectedListener function on Spinner to listen for selection changes
            townHallSpinner?.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }

                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        if (town != "Selecciona una opción*" && town.isNotEmpty()) {
                            progressValue -= 10
                            completed -= 1
                            button5.isEnabled =
                                validations.checkFieldsProgressBar(
                                    zipCode,
                                    colonyUser,
                                    streetUser,
                                    exteriorNumberString,
                                    country,
                                    state,
                                    town,
                                    completed
                                )
                        }
                        town = townHallSpinner.selectedItem.toString()
                        if (town != "Selecciona una opción*") {
                            progressValue += 10
                            completed += 1
                            button5.isEnabled =
                                validations.checkFieldsProgressBar(
                                    zipCode,
                                    colonyUser,
                                    streetUser,
                                    exteriorNumberString,
                                    country,
                                    state,
                                    town,
                                    completed
                                )
                        }
                        else{
                            button5.isEnabled = false
                        }
                        progressInForm(progressValue, completed)
                    }
                }
            //---

            //onClickListener on continueButton to listen for saveUserData in SharedPreferences
            button5.setOnClickListener { view: View ->
                view.findNavController()
                    .navigate(R.id.action_EKPTCreateAccountRegisterFormFragment_to_EKTPCreateAccountContractsFragment)
            }
            //---

            //onClickListener on appBar BackButton to popBackStack fragment
            backAppbarButton.setOnClickListener { view: View ->
                findNavController().popBackStack()
            }
            //---

            return root
        }
    }
    //ProgressInForm to draw onScreen the user progress according to the added information
    private fun progressInForm(proValBar: Int, proTexVal: Int) {
        binding.progressBar.progress = proValBar
        if (proTexVal <= 7) {
            binding.barCounter.text = "$proTexVal/7"
        }
    }
    //---

}