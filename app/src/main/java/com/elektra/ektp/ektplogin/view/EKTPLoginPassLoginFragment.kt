package com.elektra.ektp.ektplogin.view

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.isGone
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.elektra.ektp.R
import com.elektra.ektp.databinding.FragmentEKTPLoginPassLoginBinding
import com.elektra.ektp.ektpbiometricutil.EKTPBiometricUtil
import com.elektra.ektp.ektpcreateaccount.view.EKTPCreateAccountActivity
import com.elektra.ektp.ektpforgottenpass.view.EKTPForgottenPassActivity
import com.elektra.ektp.ektphome.view.EKTPHomeActivity
import com.elektra.ektp.ektplogin.viewmodel.EKTPLoginActivityViewModel
import com.elektra.ektp.ektplogin.viewmodel.EKTPLoginPassLoginViewModel

class EKTPLoginPassLoginFragment : Fragment() {

    private lateinit var binding: FragmentEKTPLoginPassLoginBinding
    private lateinit var noUserAlertLayout: View
    private lateinit var acceptButton: Button

    private var activityViewModel = EKTPLoginActivityViewModel()
    private var viewModel = EKTPLoginPassLoginViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        activityViewModel.setBiometricLogin(false)
        binding =  DataBindingUtil.inflate<FragmentEKTPLoginPassLoginBinding>(inflater,R.layout.fragment_e_k_t_p_login_pass_login, container, false)
        noUserAlertLayout = layoutInflater.inflate(R.layout.no_user_alert_layout,null)

        val checkBioStatus = viewModel.getSavedDataLogin()[0].toInt()//get the biostastus from sharedpreferences trough viewmodel
        val bioUsed = viewModel.getSavedDataLogin()[1].toInt()//get bioUsed from sharedpreferences trough viewmodel
        val userName = viewModel.getSavedDataLogin()[3]//get the userName from sharedpreferences trough viewmodel

        //no user alertdialig builder
        var noUserDialog: AlertDialog? = null
        val noUserDialogBuilder = AlertDialog.Builder(requireContext())

        noUserDialogBuilder.setView(noUserAlertLayout)
        acceptButton = noUserAlertLayout.findViewById(R.id.acceptButton)
        noUserDialog = noUserDialogBuilder.create()
        //--

        //if biometric status isn´t ok disable the button to access at that fragment
        if (checkBioStatus!=1){
            binding.biometricSignInButton.isGone = true
            binding.backAppbarButton.isGone = true
        }
        //--

        //set the string and drawable for the button for login trough biometric
        if (bioUsed==1){
            binding.biometricSignInButton.text = "Entrar con FaceID"
            binding.biometricSignInButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_on_button_face_icon, 0, 0, 0)
        }

        //Check for username if there are no user name can considerate a new user an show no user alertDialog
        if(userName == null || userName == "")
        {
            noUserDialog.show()
            binding.loginPassButton.isEnabled = false
        }
        //--

        //no user alert dialog button clicklistner
        acceptButton.setOnClickListener {
            noUserDialog.dismiss()
        }
        //--


        //main layout buttons
        with(binding){
            backAppbarButton.setOnClickListener { view: View ->
            requireActivity()
                .supportFragmentManager
                .beginTransaction()
                .replace(R.id.loginNavHostFragment, EKTPLoginBiometricLoginFragment())
                .commitNow()
            }

            frgPassClickTextView.setOnClickListener{ view: View ->
                val intent = Intent(activity, EKTPForgottenPassActivity::class.java)
                val context = view?.context
                context?.startActivity(intent)
            }

            loginPassButton.setOnClickListener { view : View ->
                val intent = Intent(activity, EKTPHomeActivity::class.java)
                val context = view?.context
                context?.startActivity(intent)
            }

            biometricSignInButton.setOnClickListener { view: View ->
                requireActivity()
                    .supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.loginNavHostFragment, EKTPLoginBiometricLoginFragment())
                    .commitNow()
            }

            createAccountTextView.setOnClickListener{view: View ->
                val intent = Intent(activity, EKTPCreateAccountActivity::class.java)
                val context = view.context
                context.startActivity(intent)
            }
            
        }
        return binding.root
    }

}