package com.elektra.ektp.ektplogin.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.elektra.ektp.R
import com.elektra.ektp.databinding.FragmentEKTPLoginBiometricLoginBinding
import com.elektra.ektp.ektpcreateaccount.view.EKTPCreateAccountActivity
import com.elektra.ektp.ektpcreateaccount.view.EKTPCreateAccountFragment
import com.elektra.ektp.ektpbiometricutil.EKTPBiometricUtil
import com.elektra.ektp.ektphome.view.EKTPHomeActivity
import com.elektra.ektp.ektptoaster.EKTPToaster
import java.util.concurrent.Executor

class EKTPLoginBiometricLoginFragment : Fragment() {

    private lateinit var binding: FragmentEKTPLoginBiometricLoginBinding
    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentEKTPLoginBiometricLoginBinding>(inflater,R.layout.fragment_e_k_t_p_login_biometric_login, container, false)
        val bioUsed = EKTPBiometricUtil().determineBio(requireContext())

        executor = ContextCompat.getMainExecutor(requireContext())

        biometricPrompt = androidx.biometric.BiometricPrompt(this,executor,object:androidx.biometric.BiometricPrompt.AuthenticationCallback(){
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                //messageOnToast("$errString")
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                EKTPToaster().makeAToast(requireContext(),"autorizacion con exito")
                val intent = Intent(activity, EKTPHomeActivity::class.java)
                val context = view?.context
                context?.startActivity(intent)
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                //messageOnToast("algo fallo")
            }
        })

        promptInfo = androidx.biometric.BiometricPrompt.PromptInfo.Builder()
            .setTitle("Autenticacion Biometrica")
            .setSubtitle("usa el biometrico de tu celular")
            .setNegativeButtonText("cancelar")
            .build()

        if (bioUsed == 1){
            binding.biometricLoginImageButton.setBackgroundDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.ic_face_button))
            binding.biometricInfoTextView.text = "Entrar con FaceID"
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
            val context =view.context
            context.startActivity(intent)
        }

        return binding.root
    }

}