package com.elektra.ektp.ektpcreateaccount.view

import android.content.Context
import androidx.biometric.BiometricPrompt
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.elektra.ektp.R
import com.elektra.ektp.databinding.FragmentEktpCreateAccountBiometricsActivationBinding
import com.elektra.ektp.ektpsharedpreferences.EKTPUserApplication.Companion.preferences
import com.elektra.ektp.ektptoaster.EKTPToaster
import com.elektra.ektp.uservalidations.KeyGenUtil
import java.util.concurrent.Executor

class EKTPCreateAccountBiometricsActivationFragment : Fragment() {

    private lateinit var binding : FragmentEktpCreateAccountBiometricsActivationBinding
    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo
    private val bioUsed = preferences.getBioType()
    val toast = EKTPToaster()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentEktpCreateAccountBiometricsActivationBinding>(inflater,
            R.layout.fragment_ektp_create_account_biometrics_activation, container, false)

        executor = ContextCompat.getMainExecutor(requireContext())

        biometricPrompt = androidx.biometric.BiometricPrompt(this,executor,object:androidx.biometric.BiometricPrompt.AuthenticationCallback(){
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                toast.makeAToast(activity as Context, "Error $errString")
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)

                val pair = KeyGenUtil().encryptData("Test")

                //val decryptedData = KeyGenUtil().decryptData(pair.first, pair.second)

                val encrypted = pair.second.toString(Charsets.UTF_8)
                //println("Encrypted data: $encrypted")
                //println("Decrypted data: $decryptedData")

                preferences.saveEncryptToken(encrypted)

                toast.makeAToast(activity as Context, "Guardado con éxito")
                view?.findNavController()?.navigate(R.id.action_EKTPCreateAccountBiometricsActivationFragment_to_EKTPCreateAccountSuccessfulFragment)
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                toast.makeAToast(activity as Context, "Algo falló")
            }
        })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Autenticacion Biométrica")
            .setSubtitle("Usa el biométrico de tu teléfono")
            .setNegativeButtonText("Cancelar")
            .build()

        if (bioUsed == 1){
            binding.sectionTitleAppbarTextView.text = "Activar Face ID"
            binding.textViewBio.text = "¿Te gustaría activar tu Face ID"
            binding.textViewBio2.text = "Tu Rostro será tu clave de seguridad y contraseña"
            binding.imageButtonBio.setBackgroundResource(R.drawable.ic_active_face_button)
            binding.textViewBio3.text = "Toca el ícono para activar tu Face ID"
        }

        binding.backAppbarButton.setOnClickListener { view: View ->
            findNavController().popBackStack()
        }

        binding.textViewBio4.setOnClickListener{view: View ->
            view.findNavController().navigate(R.id.action_EKTPCreateAccountBiometricsActivationFragment_to_EKTPCreateAccountSuccessfulFragment)
        }

        binding.imageButtonBio.setOnClickListener { view: View ->
            biometricPrompt.authenticate(promptInfo)
        }

        return binding.root
    }

}