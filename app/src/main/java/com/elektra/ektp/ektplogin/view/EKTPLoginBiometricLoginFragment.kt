package com.elektra.ektp.ektplogin.view

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.OnBackPressedCallback
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
import com.elektra.ektp.ektputilies.ektptoaster.EKTPToaster
import java.util.concurrent.Executor

class EKTPLoginBiometricLoginFragment : Fragment() {

    private lateinit var binding: FragmentEKTPLoginBiometricLoginBinding
    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo
    //widgets for alertDialogs
    private lateinit var retryButton: Button
    private lateinit var cancelButton: Button
    private lateinit var acceptButton: Button
    private lateinit var noServiceAccept: Button
    private lateinit var bioAlertLayout: View
    private lateinit var noUserAlertLayout: View
    private lateinit var noServiceAlertLayout: View
    //---
    private val activityViewModel = EKTPLoginActivityViewModel()//instance of activity viewModel used as shared Viewmodel
    private val viewModel = EKTPLoginBiometricLoginViewModel()//instance of fragment viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Overriding obBackPressed to popBackStack fragment
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity?.finish()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflater layout for this fragment
        //activityViewModel.setBiometricLogin(true)//is this biometricLogin?

        binding = DataBindingUtil.inflate<FragmentEKTPLoginBiometricLoginBinding>(inflater,R.layout.fragment_e_k_t_p_login_biometric_login, container, false)
        noUserAlertLayout = layoutInflater.inflate(R.layout.no_user_alert_layout,null)//inlfater for the no user registred case
        noServiceAlertLayout = layoutInflater.inflate(R.layout.no_service_alert_layout,null)// inflater for the no service case

        val noServiceAlertDialog = alertDialogOpener(noServiceAlertLayout, requireContext())
        val noUserAlertDialog = alertDialogOpener(noUserAlertLayout,requireContext())

        //inflate diferent layout for the dialog depeding biotype
        if (viewModel.getSavedDataLogin()[1].toInt() == 1) {//get the biotype 1 = face, 2 = iris, 3 = fingerprint
            bioAlertLayout = layoutInflater.inflate(R.layout.unrecognized_face_alert_layout, null)
            binding.biometricLoginImageButton.setBackgroundDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.ic_face_button))
            binding.biometricInfoTextView.text = resources.getString(R.string.login_face_label)
        }
        else {
            bioAlertLayout = layoutInflater.inflate(R.layout.unrecognized_finger_alert_layout,null)
        }
        val bioAlertDialog = alertDialogOpener(bioAlertLayout,requireContext())
        //---

        //find the views for bio alertDialog
        retryButton = bioAlertLayout.findViewById(R.id.biometricRetryButton)
        cancelButton = bioAlertLayout.findViewById(R.id.biometricCancelButton)
        //--
        //no user alertDialog build
        acceptButton = noUserAlertLayout.findViewById(R.id.acceptButton)
        //---
        //no service alertDialog Build
        noServiceAccept = noServiceAlertLayout.findViewById(R.id.acceptButton)
        //---

        executor = ContextCompat.getMainExecutor(requireContext())
        //biometric prompt actioner
        biometricPrompt = androidx.biometric.BiometricPrompt(this,executor,object:androidx.biometric.BiometricPrompt.AuthenticationCallback(){
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)//execute if there are an error
                //messageOnToast("$errString")

            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)//execute if pass the auth
                        if ((0..1).random() == 2){//50% probabilities to make appear the case when there are no service //changed 0 for 2
                            noServiceAlertDialog.show()
                        }else{
                            openActivity(EKTPHomeActivity())
                            activity?.finish()
                        }
                    }
                //--
            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()//execute if dont pass the auth
                biometricPrompt.cancelAuthentication()
                bioAlertDialog.show()
            }
        })
        //---
        //info show in biometric popup
        promptInfo = androidx.biometric.BiometricPrompt.PromptInfo.Builder()
            .setTitle(getString(R.string.biometrics_title))
            .setSubtitle(getString(R.string.biometrics_subtitle))
            .setNegativeButtonText(getString(R.string.biometrics_cancel))
            .build()
        //---

        //biometric alertDialog click listener
        cancelButton.setOnClickListener {
            bioAlertDialog.dismiss()
        }
        retryButton.setOnClickListener {
            biometricPrompt.authenticate(promptInfo)
        }
        //--
        //no user alertDialog button clicklistener
        acceptButton.setOnClickListener {
            openActivity(EKTPCreateAccountActivity())
            activity?.finish()
        }
        //--
        //no service alertDialog button clicklistener
        noServiceAccept.setOnClickListener {
            noServiceAlertDialog.dismiss()
        }
        //--

        //layoutbuttons
        with(binding){
            biometricLoginImageButton.setOnClickListener {
                if(viewModel.getSavedDataLogin()[3] == "") { //Check for username if there are no user name can considerate a new user an show no user alertDialog
                    noUserAlertDialog.show()
                }else{
                    biometricPrompt.authenticate(promptInfo)
                }
            }
            passSignInButton.setOnClickListener{
                requireActivity()
                    .supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.loginNavHostFragment, EKTPLoginPassLoginFragment())
                    .commitNow()
            }
            createAccountTextView.setOnClickListener{
                openActivity(EKTPCreateAccountActivity())
                activity?.finish()
            }
        }
        //---
        return binding.root
    }

    private fun alertDialogOpener(dialogLayout: View, context: Context): AlertDialog{
        var alertDialog: AlertDialog? = null
        val alertDialogBuilder = AlertDialog.Builder(context)

        alertDialogBuilder.setView(dialogLayout)
        alertDialog = alertDialogBuilder.create()

        return alertDialog
    }

    private fun openActivity(activityName: Activity){
        val intent = Intent(activity, activityName::class.java)
        val context = view?.context
        context?.startActivity(intent)
    }

}