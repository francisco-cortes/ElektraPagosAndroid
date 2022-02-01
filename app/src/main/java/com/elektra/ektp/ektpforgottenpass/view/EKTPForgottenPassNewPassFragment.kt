package com.elektra.ektp.ektpforgottenpass.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.elektra.ektp.R
import com.elektra.ektp.databinding.FragmentEktpForgottenPassNewPassBinding
import com.elektra.ektp.uservalidations.UserValidations

class EKTPForgottenPassNewPassFragment : Fragment() {

    private lateinit var binding: FragmentEktpForgottenPassNewPassBinding
    private val validations = UserValidations()
    private var showPassVar = false
    private var showPassVar2 = false
    private var passTextVar = ""
    private var passTextVar2 = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_ektp_forgotten_pass_new_pass, container, false)

        binding.buttonNewPass.isEnabled = false
        binding.matchPass.isVisible = false

        return binding.root
    }

}