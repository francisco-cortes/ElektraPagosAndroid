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
import androidx.fragment.app.viewModels
import com.elektra.ektp.R
import com.elektra.ektp.databinding.FragmentEktpForgottenPassNewPassBinding
import com.elektra.ektp.databinding.FragmentEktpForgottenPassPhoneNumberBinding
import com.elektra.ektp.ektpforgottenpass.view.viewmodel.EKTPForgottenPassPhoneNumberViewModel
import com.elektra.ektp.uservalidations.UserValidations

class EKTPForgottenPassPhoneNumberFragment : Fragment() {

    private lateinit var binding: FragmentEktpForgottenPassPhoneNumberBinding
    private val phoneNumberViewModel: EKTPForgottenPassPhoneNumberViewModel by viewModels()
    private lateinit var phoneNumber: String
    private val validations = UserValidations()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
        R.layout.fragment_ektp_forgotten_pass_phone_number, container, false)

        binding.buttonForgottenPas.isEnabled = false

        binding.phoneNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                phoneNumber = s.toString()
                if (validations.checkPhoneNumber(phoneNumber)){
                    binding.phoneNumber.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    binding.toastPhoneInvalid.isVisible = false
                    binding.buttonForgottenPas.isEnabled = true
                }
                else{
                    binding.phoneNumber.setBackgroundResource(R.drawable.validation_edit_text)
                    binding.toastPhoneInvalid.isVisible = true
                    binding.buttonForgottenPas.isEnabled = false
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

        return binding.root
    }
}