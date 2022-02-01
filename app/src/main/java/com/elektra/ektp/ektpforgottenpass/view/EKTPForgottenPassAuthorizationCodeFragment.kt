package com.elektra.ektp.ektpforgottenpass.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.elektra.ektp.R
import com.elektra.ektp.databinding.FragmentEktpForgottenPassAuthorizationCodeBinding
import com.elektra.ektp.databinding.FragmentEktpForgottenPassPhoneNumberBinding
import com.elektra.ektp.ektpforgottenpass.view.viewmodel.EKTPForgottenPassAuthorizationCodeViewModel

class EKTPForgottenPassAuthorizationCodeFragment : Fragment() {

    private lateinit var binding: FragmentEktpForgottenPassAuthorizationCodeBinding
    private val authorizationViewModel: EKTPForgottenPassAuthorizationCodeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
        R.layout.fragment_ektp_forgotten_pass_authorization_code, container, false)

        return binding.root
    }
}