package com.elektra.ektp.ektplogin.view

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
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
    private lateinit var incorrectPasswordLayout: View
    private lateinit var lockedPasswordLayout: View

    private lateinit var noUserAcceptButton: Button
    private lateinit var incorrectPassAcceptButton: Button
    private lateinit var passAttemptTextView: TextView
    private lateinit var lockedCancelButton: Button
    private lateinit var lockedUnlockButton: Button

    private var activityViewModel = EKTPLoginActivityViewModel()
    private var viewModel = EKTPLoginPassLoginViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        activityViewModel.setBiometricLogin(false)
        binding =  DataBindingUtil.inflate<FragmentEKTPLoginPassLoginBinding>(inflater,R.layout.fragment_e_k_t_p_login_pass_login, container, false)
        noUserAlertLayout = layoutInflater.inflate(R.layout.no_user_alert_layout,null)//no user dialog layout
        incorrectPasswordLayout = layoutInflater.inflate(R.layout.incorrect_password_alert_layout,null)//no user dialog layout
        lockedPasswordLayout = layoutInflater.inflate(R.layout.locked_password_alert_layout,null)//locked pasword layout

        val checkBioStatus = viewModel.getSavedDataLogin()[0].toInt()//get the biostastus from sharedpreferences trough viewmodel
        val bioUsed = viewModel.getSavedDataLogin()[1].toInt()//get bioUsed from sharedpreferences trough viewmodel
        val userName = viewModel.getSavedDataLogin()[3]//get the userName from sharedpreferences trough viewmodel
        val password = viewModel.getSavedDataLogin()[4]
        val isLocked = viewModel.getSavedDataLogin()[5].toBoolean()

        var passwordAttempt = 0

        //no user alertdialig builder
        var noUserDialog: AlertDialog? = null
        val noUserDialogBuilder = AlertDialog.Builder(requireContext())

        noUserDialogBuilder.setView(noUserAlertLayout)
        noUserAcceptButton = noUserAlertLayout.findViewById(R.id.acceptButton)
        noUserDialog = noUserDialogBuilder.create()
        //---

        //incorrect password alertDialog builder
        var incorrectPassDialog : AlertDialog? = null
        val incorrectPassDialogBuilder = AlertDialog.Builder(requireContext())

        incorrectPassDialogBuilder.setView(incorrectPasswordLayout)
        incorrectPassAcceptButton = incorrectPasswordLayout.findViewById(R.id.acceptButton)
        passAttemptTextView = incorrectPasswordLayout.findViewById(R.id.passwordAttemptTextView)
        incorrectPassDialog = incorrectPassDialogBuilder.create()
        //---

        //locked password alert dialog builder
        var lockedPasswordDialog: AlertDialog? = null
        val lockedPasswordDialogBuilder = AlertDialog.Builder(requireContext())

        lockedPasswordDialogBuilder.setView(lockedPasswordLayout)
        lockedCancelButton = lockedPasswordLayout.findViewById(R.id.cancelButton)
        lockedUnlockButton = lockedPasswordLayout.findViewById(R.id.unlockButton)
        lockedPasswordDialog = lockedPasswordDialogBuilder.create()
        //----


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
        noUserAcceptButton.setOnClickListener {
            noUserDialog.dismiss()
        }
        //--

        //incorrect pass alert dialog buttons
        incorrectPassAcceptButton.setOnClickListener {
            incorrectPassDialog.dismiss()
        }
        //--

        //locked password alert dialog button
        lockedCancelButton.setOnClickListener {
            lockedPasswordDialog.dismiss()
        }

        lockedUnlockButton.setOnClickListener {
            passwordAttempt = 0
            lockedPasswordDialog.dismiss()
        }


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
                activity?.finish()
                val intent = Intent(activity, EKTPForgottenPassActivity::class.java)
                val context = view?.context
                context?.startActivity(intent)
            }

            loginPassButton.setOnClickListener { view : View ->
                val passwordInput = editTextTextPassword.text.toString()
                if (password == passwordInput){
                    activity?.finish()
                    val intent = Intent(activity, EKTPHomeActivity::class.java)
                    val context = view?.context
                    context?.startActivity(intent)
                }else{
                    if (passwordAttempt >= 3){
                        lockedPasswordDialog.show()
                    }else {
                        passwordAttempt ++
                        passAttemptTextView.text = resources.getText(R.string.incorrect_password_attempt_label).toString() + passwordAttempt.toString()
                        incorrectPassDialog.show()
                    }
                }
            }

            biometricSignInButton.setOnClickListener { view: View ->
                requireActivity()
                    .supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.loginNavHostFragment, EKTPLoginBiometricLoginFragment())
                    .commitNow()
            }

            createAccountTextView.setOnClickListener{view: View ->
                activity?.finish()
                val intent = Intent(activity, EKTPCreateAccountActivity::class.java)
                val context = view.context
                context.startActivity(intent)
            }

        }
        return binding.root
    }

}