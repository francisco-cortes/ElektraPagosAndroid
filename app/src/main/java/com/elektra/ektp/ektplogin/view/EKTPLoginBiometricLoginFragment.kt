package com.elektra.ektp.ektplogin.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.elektra.ektp.R
import com.elektra.ektp.databinding.FragmentEKTPLoginBiometricLoginBinding
import com.elektra.ektp.ektpcreateaccount.view.EKTPCreateAccountFragment

class EKTPLoginBiometricLoginFragment : Fragment() {

    private lateinit var binding: FragmentEKTPLoginBiometricLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentEKTPLoginBiometricLoginBinding>(inflater,R.layout.fragment_e_k_t_p_login_biometric_login, container, false)

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
            val intent = Intent(activity, EKTPCreateAccountFragment::class.java)
            val context =view.context
            context.startActivity(intent)
        }

        return binding.root
    }

}