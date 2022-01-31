package com.elektra.ektp.ektpcreateaccount.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    private var completed = 0
    private lateinit var insertName: String
    private lateinit var paternalLastName: String
    private lateinit var maternalLastName: String
    private lateinit var birthDate: String
    private lateinit var birthState: String
    private lateinit var gender: String
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


        return binding.root
    }

    private fun progressInForm(proValBar: Int, proTexVal: Int) {
        binding.progressBar.progress = proValBar
        if (proTexVal <= 7) {
            binding.barCounter.text = "$proTexVal/7"
        }
    }

}