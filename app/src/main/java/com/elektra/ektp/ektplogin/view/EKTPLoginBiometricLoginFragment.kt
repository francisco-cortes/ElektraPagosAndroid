package com.elektra.ektp.ektplogin.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.elektra.ektp.R
import com.elektra.ektp.databinding.FragmentEKTPLoginBiometricLoginBinding

class EKTPLoginBiometricLoginFragment : Fragment() {

    private lateinit var binding: FragmentEKTPLoginBiometricLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentEKTPLoginBiometricLoginBinding>(inflater,R.layout.fragment_e_k_t_p_login_biometric_login, container, false)
        return binding.root
    }

}