package com.elektra.ektp.ektplogin.view

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

    private lateinit var retryButton: Button
    private lateinit var cancelButton: Button
    private lateinit var acceptButton: Button
    private lateinit var bioAlertLayout: View
    private lateinit var noUserAlertLayout: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout para este fragmento
        EKTPLoginActivityViewModel().setBiometricLogin(true)
        binding = DataBindingUtil.inflate<FragmentEKTPLoginBiometricLoginBinding>(inflater,R.layout.fragment_e_k_t_p_login_biometric_login, container, false)
        loginData = EKTPLoginBiometricLoginViewModel().getSavedDataLogin()
        noUserAlertLayout = layoutInflater.inflate(R.layout.no_user_alert_layout,null)

        val userName = EKTPLoginBiometricLoginViewModel().getSavedDataLogin()[3]
        val bioUsed = EKTPLoginBiometricLoginViewModel().getSavedDataLogin()[1].toInt()
        var biometricDialog: AlertDialog? = null
        var noUserDialog: AlertDialog? = null
        val biometricDialogBuilder = AlertDialog.Builder(requireContext())
        val noUserDialogBuilder = AlertDialog.Builder(requireContext())


        // usar un layout personalizado para el alertdialog
        if (bioUsed == 1) {
            bioAlertLayout = layoutInflater.inflate(R.layout.unrecognized_face_alert_layout, null)
            binding.biometricLoginImageButton.setBackgroundDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.ic_face_button))
            binding.biometricInfoTextView.text = "Entrar con FaceID"
        }
        else {
            bioAlertLayout = layoutInflater.inflate(R.layout.unrecognized_finger_alert_layout,null)
        }

        biometricDialogBuilder.setView(bioAlertLayout)
        retryButton = bioAlertLayout.findViewById(R.id.biometricRetryButton)
        cancelButton = bioAlertLayout.findViewById(R.id.biometricCancelButton)
        // se crea el dialogo con el layout nesesario
        biometricDialog = biometricDialogBuilder.create()

        //no user build
        noUserDialogBuilder.setView(noUserAlertLayout)
        acceptButton = noUserAlertLayout.findViewById(R.id.acceptButton)
        noUserDialog = noUserDialogBuilder.create()

        executor = ContextCompat.getMainExecutor(requireContext())

        biometricPrompt = androidx.biometric.BiometricPrompt(this,executor,object:androidx.biometric.BiometricPrompt.AuthenticationCallback(){
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                //messageOnToast("$errString")

            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                val intent = Intent(activity, EKTPHomeActivity::class.java)
                val context = view?.context
                context?.startActivity(intent)
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                //messageOnToast("algo fallo")
                biometricDialog.show()
                biometricPrompt.cancelAuthentication()

            }
        })

        promptInfo = androidx.biometric.BiometricPrompt.PromptInfo.Builder()
            .setTitle("Autenticacion Biometrica")
            .setSubtitle("usa el biometrico de tu celular")
            .setNegativeButtonText("cancelar")
            .build()

        //botones del alert dialog
        cancelButton.setOnClickListener {
            biometricDialog.dismiss()
        }

        retryButton.setOnClickListener {
            biometricPrompt.authenticate(promptInfo)
        }
        //botones de alert dialog

        if(userName == null || userName == "")
        {
            noUserDialog.show()
            binding.biometricLoginImageButton.isEnabled = false
        }

        acceptButton.setOnClickListener {
            noUserDialog.dismiss()
        }

        binding.biometricLoginImageButton.setOnClickListener {
            biometricPrompt.authenticate(promptInfo)
        }

        binding.passSignInButton.setOnClickListener{view: View ->
            requireActivity()
                .supportFragmentManager
                .beginTransaction()
                .replace(R.id.loginNavHostFragment, EKTPLoginPassLoginFragment())
                .commitNow()
            /*if(fragment != null){
                requireActivity()
                    .supportFragmentManager
                    .beginTransaction()
                    .remove(fragment)
                    .commit();
            }*/

        }

        binding.createAccountTextView.setOnClickListener{view: View ->
            val intent = Intent(activity, EKTPCreateAccountActivity::class.java)
            val context = view.context
            context.startActivity(intent)
        }

        return binding.root
    }

}