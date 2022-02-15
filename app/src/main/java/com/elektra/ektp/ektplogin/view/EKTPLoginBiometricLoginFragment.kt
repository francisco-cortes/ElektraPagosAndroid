package com.elektra.ektp.ektplogin.view

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.elektra.ektp.R
import com.elektra.ektp.databinding.FragmentEKTPLoginBiometricLoginBinding
import com.elektra.ektp.ektpcreateaccount.view.EKTPCreateAccountActivity
import com.elektra.ektp.ektpcreateaccount.view.EKTPCreateAccountFragment
import com.elektra.ektp.ektpbiometricutil.EKTPBiometricUtil
import com.elektra.ektp.ektphome.view.EKTPHomeActivity
import com.elektra.ektp.ektplogin.viewmodel.EKTPLoginActivityViewModel
import com.elektra.ektp.ektplogin.viewmodel.EKTPLoginBiometricLoginViewModel
import com.elektra.ektp.ektptoaster.EKTPToaster
import java.util.concurrent.Executor

class EKTPLoginBiometricLoginFragment : Fragment() {

    private lateinit var binding: FragmentEKTPLoginBiometricLoginBinding
    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo
    private lateinit var loginData: ArrayList<String>
    //widgets for alertDialogs
    private lateinit var retryButton: Button
    private lateinit var cancelButton: Button
    private lateinit var acceptButton: Button
    private lateinit var bioAlertLayout: View
    private lateinit var noUserAlertLayout: View
    //---
    private val activityViewModel = EKTPLoginActivityViewModel()//instance of activity viewModel used as shared Viewmodel
    private val viewModel = EKTPLoginBiometricLoginViewModel()//instance of fragment viewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflater layout for this fragment
        activityViewModel.setBiometricLogin(true)//is this biometricLogin?

        binding = DataBindingUtil.inflate<FragmentEKTPLoginBiometricLoginBinding>(inflater,R.layout.fragment_e_k_t_p_login_biometric_login, container, false)
        noUserAlertLayout = layoutInflater.inflate(R.layout.no_user_alert_layout,null)

        loginData = viewModel.getSavedDataLogin()//get userData from shared preferences

        val bioUsed = viewModel.getSavedDataLogin()[1].toInt()//get the biotype 1 = face, 2 = iris, 3 = fingerprint
        //var used in Dialog Build
        var biometricDialog: AlertDialog? = null
        var noUserDialog: AlertDialog? = null
        val biometricDialogBuilder = AlertDialog.Builder(requireContext())
        val noUserDialogBuilder = AlertDialog.Builder(requireContext())
        //---


        //inflate diferent layout for the dialog depeding biotype
        if (bioUsed == 1) {
            bioAlertLayout = layoutInflater.inflate(R.layout.unrecognized_face_alert_layout, null)
            binding.biometricLoginImageButton.setBackgroundDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.ic_face_button))
            binding.biometricInfoTextView.text = "Entrar con FaceID"
        }
        else {
            bioAlertLayout = layoutInflater.inflate(R.layout.unrecognized_finger_alert_layout,null)
        }
        //---

        //find the views for bio alertDialog
        biometricDialogBuilder.setView(bioAlertLayout)
        retryButton = bioAlertLayout.findViewById(R.id.biometricRetryButton)
        cancelButton = bioAlertLayout.findViewById(R.id.biometricCancelButton)
        // creates de biometric alertDialog
        biometricDialog = biometricDialogBuilder.create()
        //--

        //no user alertDialog build
        noUserDialogBuilder.setView(noUserAlertLayout)
        acceptButton = noUserAlertLayout.findViewById(R.id.acceptButton)
        noUserDialog = noUserDialogBuilder.create()

        executor = ContextCompat.getMainExecutor(requireContext())
        //--

        //biometric prompt actioner
        biometricPrompt = androidx.biometric.BiometricPrompt(this,executor,object:androidx.biometric.BiometricPrompt.AuthenticationCallback(){
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)//execute if there are an error
                //messageOnToast("$errString")

            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)//execute if pass the auth
                openActivity(EKTPHomeActivity())
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()//execute if dont pass the auth
                biometricPrompt.cancelAuthentication()
                biometricDialog.show()

            }
        })
        //---

        //info show in biometric popup
        promptInfo = androidx.biometric.BiometricPrompt.PromptInfo.Builder()
            .setTitle("Autenticacion Biometrica")
            .setSubtitle("usa el biometrico de tu celular")
            .setNegativeButtonText("cancelar")
            .build()
        //---

        //biometric alertDialog click listener
        cancelButton.setOnClickListener {
            biometricDialog.dismiss()
        }

        retryButton.setOnClickListener {
            biometricPrompt.authenticate(promptInfo)
        }
        //--

        //Check for username if there are no user name can considerate a new user an show no user alertDialog
        if(viewModel.getSavedDataLogin()[3] == "")
        {
            noUserDialog.show()
            binding.biometricLoginImageButton.isEnabled = false
        }
        //--

        //no user alertDialog button clicklistener
        acceptButton.setOnClickListener {
            noUserDialog.dismiss()
        }
        //--

        //layoutbuttons
        with(binding){
            biometricLoginImageButton.setOnClickListener {
                biometricPrompt.authenticate(promptInfo)
            }
            passSignInButton.setOnClickListener{view: View ->
                requireActivity()
                    .supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.loginNavHostFragment, EKTPLoginPassLoginFragment())
                    .commitNow()
            }
            createAccountTextView.setOnClickListener{view: View ->
                openActivity(EKTPCreateAccountActivity())
                activity?.finish()
            }
        }
        //---
        return binding.root
    }

    private fun openActivity(activityName: Activity){
        val intent = Intent(activity, activityName::class.java)
        val context = view?.context
        context?.startActivity(intent)
    }

}