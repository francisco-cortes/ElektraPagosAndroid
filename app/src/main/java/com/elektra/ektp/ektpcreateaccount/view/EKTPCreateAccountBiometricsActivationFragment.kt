package com.elektra.ektp.ektpcreateaccount.view

import android.content.Context
import androidx.biometric.BiometricPrompt
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.elektra.ektp.R
import com.elektra.ektp.databinding.FragmentEktpCreateAccountBiometricsActivationBinding
import com.elektra.ektp.ektpcreateaccount.viewmodel.EKTPCReateAccountBiometricsActivationViewModel
import com.elektra.ektp.ektpsharedpreferences.EKTPUserApplication.Companion.preferences
import com.elektra.ektp.ektputilies.ektptoaster.EKTPToaster
import java.util.concurrent.Executor

class EKTPCreateAccountBiometricsActivationFragment : Fragment() {

    //Global databinding access variable
    private lateinit var binding : FragmentEktpCreateAccountBiometricsActivationBinding

    //General biometrics access variables
    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo
    //---

    //SharedPreferences access variable
    private val viewModel = EKTPCReateAccountBiometricsActivationViewModel()

    //Toaster fuction variable
    val toast = EKTPToaster()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Overriding obBackPressed to popBackStack fragment
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_ektp_create_account_biometrics_activation, container, false)

        executor = ContextCompat.getMainExecutor(requireContext())

        //System authentication constructor, members methods implemented
        biometricPrompt = BiometricPrompt(this,executor,object:BiometricPrompt.AuthenticationCallback(){
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                toast.makeAToast(activity as Context, "Error $errString")
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)

                //val pair = KeyGenUtil().encryptData("Test")

                //val decryptedData = KeyGenUtil().decryptData(pair.first, pair.second)

                //val encrypted = pair.second.toString(Charsets.UTF_8)
                //println("Encrypted data: $encrypted")
                //println("Decrypted data: $decryptedData")
                viewModel.saveBioInLogin(true)
                toast.makeAToast(activity as Context, "Guardado con éxito")
                fragmentReplacer(EKTPCreateAccountSuccessfulFragment())
                //view?.findNavController()?.navigate(R.id.action_EKTPCreateAccountBiometricsActivationFragment_to_EKTPCreateAccountSuccessfulFragment)
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                toast.makeAToast(activity as Context, "Algo falló")
            }
        })
        //---

        //System authentication information to show onScreen
        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Autenticacion Biométrica")
            .setSubtitle("Usa el biométrico de tu teléfono")
            .setNegativeButtonText("Cancelar")
            .build()
        //---

        with(binding){
            /*According to api level, the authentication hardware can change,
        so the information to show changes too*/
            if (preferences.getBioType()== 1){
                sectionTitleAppbarTextView.text = getString(R.string.face_header_label)
                textViewBio.text = getString(R.string.fragment_biometrics_activation_activate_face)
                textViewBio2.text = getString(R.string.fragment_biometrics_activation_face)
                imageButtonBio.setBackgroundResource(R.drawable.ic_active_face_button)
                textViewBio3.text = getString(R.string.fragment_biometrics_activation_face)
            }
            //---

            //onClickListener on appBar BackButton to popBackStack fragment
            backAppbarButton.setOnClickListener {
                findNavController().popBackStack()
            }
            //---

            /*OnClickLIstener to TextView when user does not want
            to activate biometric authentication now*/
            textViewBio4.setOnClickListener{
                viewModel.saveBioInLogin(false)
                fragmentReplacer(EKTPCreateAccountSuccessfulFragment())
                //view.findNavController().navigate(R.id.action_EKTPCreateAccountBiometricsActivationFragment_to_EKTPCreateAccountSuccessfulFragment)
            }
            //---

            //OnClickListener to call system authentication with biometric hardware activated
            imageButtonBio.setOnClickListener {
                biometricPrompt.authenticate(promptInfo)
            }
            //---

            return root
        }
    }
    private fun fragmentReplacer(fragment: Fragment){
        requireActivity()
            .supportFragmentManager
            .beginTransaction()
            .replace(R.id.CreateAccountNavigatorHost,fragment)
            .commitNow()//open the biometric login fragment
    }
}