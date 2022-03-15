package com.elektra.ektp.ektplogin.view

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isGone
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
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
    private lateinit var noServiceAlertLayout: View
    //alert dialogs widgets
    private lateinit var noUserAcceptButton: Button
    private lateinit var incorrectPassAcceptButton: Button
    private lateinit var passAttemptTextView: TextView
    private lateinit var lockedCancelButton: Button
    private lateinit var lockedUnlockButton: Button
    private lateinit var noServiceAccept: Button
    //---
    private var showPassVar = false
    private var activityViewModel = EKTPLoginActivityViewModel()//instance for activity view Model
    private var viewModel = EKTPLoginPassLoginViewModel()// instance for fragment view Model

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Overriding obBackPressed to popBackStack fragment
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (viewModel.getSavedDataLogin()[0].toInt()!=1 || !activityViewModel.getBioLoginActivated()){
                    activity?.finish()
                }else{
                    requireActivity()
                        .supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.loginNavHostFragment, EKTPLoginBiometricLoginFragment())
                        .commitNow()//open the biometric login fragment
                }
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //activityViewModel.setBiometricLogin(false)//is biometric login?
        binding =  DataBindingUtil.inflate<FragmentEKTPLoginPassLoginBinding>(inflater,R.layout.fragment_e_k_t_p_login_pass_login, container, false)
        noUserAlertLayout = layoutInflater.inflate(R.layout.no_user_alert_layout,null)//no user dialog layout
        incorrectPasswordLayout = layoutInflater.inflate(R.layout.incorrect_password_alert_layout,null)//no user dialog layout
        lockedPasswordLayout = layoutInflater.inflate(R.layout.locked_password_alert_layout,null)//locked pasword layout
        noServiceAlertLayout = layoutInflater.inflate(R.layout.no_service_alert_layout,null)// inflater for the no service case

        var passwordAttempt = 0 // used to know how many times the password has failed

        val noUserAlertDialog = alertDialogOpener(noUserAlertLayout,requireContext())
        val incorrectPasswordAlertDialog = alertDialogOpener(incorrectPasswordLayout,requireContext())
        val noServiceAlertDialog = alertDialogOpener(noServiceAlertLayout,requireContext())
        val lockedPasswordAlertDialog = alertDialogOpener(lockedPasswordLayout,requireContext())

        //no user alertDialog builder
        noUserAcceptButton = noUserAlertLayout.findViewById(R.id.acceptButton)
        //---
        //incorrect password alertDialog builder
        incorrectPassAcceptButton = incorrectPasswordLayout.findViewById(R.id.acceptButton)
        passAttemptTextView = incorrectPasswordLayout.findViewById(R.id.passwordAttemptTextView)
        //---
        //locked password alert dialog builder
        lockedCancelButton = lockedPasswordLayout.findViewById(R.id.cancelButton)
        lockedUnlockButton = lockedPasswordLayout.findViewById(R.id.unlockButton)
        //----
        //no service alert dialog builder
        noServiceAccept = noServiceAlertLayout.findViewById(R.id.acceptButton)
        //---


        //if biometric status isn´t ok disable the button to access at that fragment
        if (viewModel.getSavedDataLogin()[0].toInt()!=1 || !activityViewModel.getBioLoginActivated()){
            binding.biometricSignInButton.isGone = true
            binding.backAppbarButton.isGone = true
        }
        //--

        //set the string and drawable for the button for login trough biometric
        if (viewModel.getSavedDataLogin()[1].toInt()==1){
            binding.biometricSignInButton.text = "Entrar con FaceID"
            binding.biometricSignInButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_on_button_face_icon, 0, 0, 0)
        }

        //no user alert dialog button clicklistner
        noUserAcceptButton.setOnClickListener {
            openActivity(EKTPCreateAccountActivity())// open the create account activity
            activity?.finish()
        }
        //--
        //incorrect pass alert dialog buttons
        incorrectPassAcceptButton.setOnClickListener {
            incorrectPasswordAlertDialog.dismiss()
        }
        //--
        //locked password alert dialog button
        lockedCancelButton.setOnClickListener {
            lockedPasswordAlertDialog.dismiss()
        }

        lockedUnlockButton.setOnClickListener {
            passwordAttempt = 0
            viewModel.saveLockedStatus(false)
            lockedPasswordAlertDialog.dismiss()
        }
        //---
        //no user alertDialog button clicklistener
        noServiceAccept.setOnClickListener {
            noServiceAlertDialog.dismiss()
        }
        //--

        if(viewModel.getSavedDataLogin()[3]==""){
            noUserAlertDialog.show()
        }

        //main layout buttons
        with(binding){

            hiUserTextView.text = viewModel.getSavedDataLogin()[3] + "!"

            hidePassButton.setOnClickListener { view: View ->
                //show an hide password text
                if (!showPassVar) {
                    passwordInputEditText.transformationMethod = HideReturnsTransformationMethod.getInstance()
                    hidePassButton.setBackgroundResource(R.drawable.ic_hide_pass)
                    showPassVar = true
                }
                else {
                    passwordInputEditText.transformationMethod = PasswordTransformationMethod.getInstance()
                    hidePassButton.setBackgroundResource(R.drawable.ic_show_pass)
                    showPassVar = false
                }
            }

            backAppbarButton.setOnClickListener { view: View ->
            requireActivity()
                .supportFragmentManager
                .beginTransaction()
                .replace(R.id.loginNavHostFragment, EKTPLoginBiometricLoginFragment())
                .commitNow()//change the fragment
            }

            frgPassClickTextView.setOnClickListener{ view: View ->
                openActivity(EKTPForgottenPassActivity())//open the forgotten pass activity
            }

            loginPassButton.setOnClickListener { view : View ->
                //check the pasword
                val passwordInput = passwordInputEditText.text.toString()

                if (!viewModel.getSavedDataLogin()[5].toBoolean()){
                    if (viewModel.getSavedDataLogin()[4] == passwordInput){
                        //50% probabilities to make appear the case when there are no service
                        if ((0..1).random() == 2){ //Changed 0 for 2
                            noServiceAlertDialog.show()
                        }else{
                            openActivity(EKTPHomeActivity())
                            activity?.finish()
                        }
                    }else{
                        if (passwordAttempt >= 3){
                            lockedPasswordAlertDialog.show()
                            viewModel.saveLockedStatus(true)
                        }else {
                            passwordAttempt ++
                            passAttemptTextView.text = resources.getText(R.string.incorrect_password_attempt_label).toString() + passwordAttempt.toString()
                            incorrectPasswordAlertDialog.show()
                        }
                    }
                }else{
                    lockedPasswordAlertDialog.show()
                }

                /*if (viewModel.getSavedDataLogin()[3]!=""){
                    if (!viewModel.getSavedDataLogin()[5].toBoolean()){
                        if (viewModel.getSavedDataLogin()[4] == passwordInput){
                            //50% probabilities to make appear the case when there are no service
                            if ((0..1).random() == 0){
                                noServiceAlertDialog.show()
                            }else{
                                openActivity(EKTPHomeActivity())
                                activity?.finish()
                            }
                        }else{
                            if (passwordAttempt >= 3){
                                lockedPasswordAlertDialog.show()
                                viewModel.saveLockedStatus(true)
                            }else {
                                passwordAttempt ++
                                passAttemptTextView.text = resources.getText(R.string.incorrect_password_attempt_label).toString() + passwordAttempt.toString()
                                incorrectPasswordAlertDialog.show()
                            }
                        }
                    }else{
                        lockedPasswordAlertDialog.show()
                    }
                }else{
                    noUserAlertDialog.show()
                }*/
            }

            biometricSignInButton.setOnClickListener { view: View ->
                requireActivity()
                    .supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.loginNavHostFragment, EKTPLoginBiometricLoginFragment())
                    .commitNow()//open the biometric login fragment
            }

            createAccountTextView.setOnClickListener{view: View ->
                openActivity(EKTPCreateAccountActivity())// open the create account activity
                activity?.finish()
            }

        }
        //---
        return binding.root
    }

    //function to open another activity trough intent
    private fun openActivity(activityName: Activity){
        val intent = Intent(activity, activityName::class.java)
        val context = view?.context
        context?.startActivity(intent)
    }
    //---

    private fun alertDialogOpener(dialogLayout: View, context: Context): AlertDialog {
        var alertDialog: AlertDialog? = null
        val alertDialogBuilder = AlertDialog.Builder(context)

        alertDialogBuilder.setView(dialogLayout)
        alertDialog = alertDialogBuilder.create()

        return alertDialog
    }

}