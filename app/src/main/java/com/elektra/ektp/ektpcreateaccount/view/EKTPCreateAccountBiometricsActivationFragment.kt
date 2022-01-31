package com.elektra.ektp.ektpcreateaccount.view

import androidx.biometric.BiometricPrompt
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.elektra.ektp.R
import com.elektra.ektp.databinding.FragmentEktpCreateAccountBiometricsActivationBinding
import com.elektra.ektp.ektpsharedpreferences.EKPTUserApplication.Companion.preferences
import java.util.concurrent.Executor

class EKTPCreateAccountBiometricsActivationFragment : Fragment() {

    private lateinit var binding : FragmentEktpCreateAccountBiometricsActivationBinding
    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo
    private val bioUsed = preferences.getBioType()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentEktpCreateAccountBiometricsActivationBinding>(inflater,
            R.layout.fragment_ektp_create_account_biometrics_activation, container, false)

        executor = ContextCompat.getMainExecutor(requireContext())

        return binding.root
    }

}