package com.elektra.ektp.ektpcreateaccount.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.elektra.ektp.R
import com.elektra.ektp.databinding.FragmentEktpCreateAccountCreatePassBinding
import com.elektra.ektp.ektpsharedpreferences.EKPTUserApplication.Companion.preferences
import com.elektra.ektp.uservalidations.UserValidations

class EKTPCreateAccountCreatePassFragment : Fragment() {

    private lateinit var binding: FragmentEktpCreateAccountCreatePassBinding
    private val validations = UserValidations()
    private var showPassVar = false
    private var showPassVar2 = false
    private var passTextVar = ""
    private var passTextVar2 = ""
    private val checkBiometricStatus = preferences.getBioStatus()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentEktpCreateAccountCreatePassBinding>(inflater,
            R.layout.fragment_ektp_create_account_create_pass, container, false)

        binding.button7.isEnabled = false
        binding.matchPass.isVisible = false

        return binding.root
    }

}