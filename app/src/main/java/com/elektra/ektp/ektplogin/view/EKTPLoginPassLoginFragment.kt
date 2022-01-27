package com.elektra.ektp.ektplogin.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.databinding.DataBindingUtil
import com.elektra.ektp.R
import com.elektra.ektp.databinding.FragmentEKTPLoginPassLoginBinding
import com.elektra.ektp.ektpbiometricutil.EKTPBiometricUtil

class EKTPLoginPassLoginFragment : Fragment() {

    private lateinit var binding: FragmentEKTPLoginPassLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  DataBindingUtil.inflate<FragmentEKTPLoginPassLoginBinding>(inflater,R.layout.fragment_e_k_t_p_login_pass_login, container, false)

        val checkBioStatus = EKTPBiometricUtil().checkBioStatus(requireContext())
        val bioUsed = EKTPBiometricUtil().determineBio(requireContext())

        if (checkBioStatus!=1){
            binding.biometricSignInButton.isGone = true
        }

        if (bioUsed==1){
            binding.biometricSignInButton.text = "Entrar con FaceID"
            binding.biometricSignInButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_on_button_face_icon, 0, 0, 0)
        }


        binding.backAppbarButton.setOnClickListener { view: View ->
            activity?.onBackPressed()
        }

        binding.biometricSignInButton.setOnClickListener { view: View ->
            requireActivity()
                .supportFragmentManager
                .beginTransaction()
                .replace(R.id.loginNavHostFragment, EKTPLoginBiometricLoginFragment())
                .commitNow()
        }
        return binding.root
    }

}