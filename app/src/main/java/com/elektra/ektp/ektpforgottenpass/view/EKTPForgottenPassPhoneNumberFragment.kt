package com.elektra.ektp.ektpforgottenpass.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.elektra.ektp.R
import com.elektra.ektp.databinding.FragmentEktpForgottenPassPhoneNumberBinding
import com.elektra.ektp.ektpforgottenpass.view.viewmodel.EKTPForgottenPassPhoneNumberViewModel
import com.elektra.ektp.ektptoaster.EKTPToaster
import com.elektra.ektp.uservalidations.UserValidations

class EKTPForgottenPassPhoneNumberFragment : Fragment(){

    private lateinit var binding: FragmentEktpForgottenPassPhoneNumberBinding
    private val phoneNumberViewModel: EKTPForgottenPassPhoneNumberViewModel by viewModels()
    private lateinit var phoneNumber: String
    private val validations = UserValidations()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        binding.buttonForgottenPas.setOnClickListener {view: View ->
            view.findNavController().navigate(R.id.action_EKTPForgottenPassPhoneNumberFragment_to_EKTPForgottenPassAuthorizationCodeFragment)
        }

        binding.backAppbarButton.setOnClickListener { view: View ->
            activity?.finish()
        }

        return binding.root
    }
}