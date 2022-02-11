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

    //Global databinding access variable
    private lateinit var binding: FragmentEktpForgottenPassPhoneNumberBinding
    //ViewModel access variable
    private val phoneNumberViewModel: EKTPForgottenPassPhoneNumberViewModel by viewModels()
    //General data variable
    private lateinit var phoneNumberString: String
    //UserValidations access variable
    private val validations = UserValidations()

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
        binding = DataBindingUtil.inflate(inflater,
        R.layout.fragment_ektp_forgotten_pass_phone_number, container, false)

        //Wrap this block code for all the lines with binding variable
        with(binding){
            buttonForgottenPas.isEnabled = false

            //TextWatcher function to listen for changes on editText
            phoneNumber.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    phoneNumberString = s.toString()
                    if (validations.checkPhoneNumber(phoneNumberString)){
                        phoneNumber.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                        toastPhoneInvalid.isVisible = false
                        buttonForgottenPas.isEnabled = true
                    }
                    else{
                        phoneNumber.setBackgroundResource(R.drawable.validation_edit_text)
                        toastPhoneInvalid.isVisible = true
                        buttonForgottenPas.isEnabled = false
                    }
                }

                override fun afterTextChanged(s: Editable?) {

                }
            })
            //---

            //onClickListener function to listen for  ask for authorization code
            buttonForgottenPas.setOnClickListener {view: View ->
                view.findNavController().navigate(R.id.action_EKTPForgottenPassPhoneNumberFragment_to_EKTPForgottenPassAuthorizationCodeFragment)
            }
            //--

            //onClickListener on appBar BackButton to destroy fragment and activity
            backAppbarButton.setOnClickListener { view: View ->
                activity?.finish()
            }
            //--

            return root
        }
        //--
    }
}