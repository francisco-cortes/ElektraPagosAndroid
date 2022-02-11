package com.elektra.ektp.ektplogin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.elektra.ektp.R
import com.elektra.ektp.databinding.ActivityLoginBinding
import com.elektra.ektp.ektpbiometricutil.EKTPBiometricUtil
import com.elektra.ektp.ektplogin.viewmodel.EKTPLoginActivityViewModel
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import kotlin.properties.Delegates


class EKTPLoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding //binding for activity layout
    private val viewModel = EKTPLoginActivityViewModel() //instance of viewModel
    private val bioChecker = EKTPBiometricUtil(this) //instance of biometricAuthUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //inflater for this layout
        binding = DataBindingUtil.setContentView<ActivityLoginBinding>(this,R.layout.activity_login)
        //check the biostatus and set the layout
        val bioStatus = bioChecker.checkBioStatus()
        val bioUsed = bioChecker.determineBio()
        if (bioStatus==1){
            if(bioUsed == 1 || bioUsed == 3){
                viewModel.setBiometricLogin(true)//variable used for navigation
                openFragment(EKTPLoginBiometricLoginFragment())//change the fragmen
            }
        } else {
            viewModel.setBiometricLogin(false)//variable use for navigaqtion
            openFragment(EKTPLoginPassLoginFragment())//cange the fragment
        }
        viewModel.saveBiometricStatus(bioStatus,bioUsed)//save the biometric status trough activity vie model
    }
    //---

    //funtion made for remplace fragments
    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            add(R.id.loginNavHostFragment, fragment)
            commit()
        }
    }
    //---
    //define for back button behavior
    override fun onBackPressed() {
        val bioStatus = bioChecker.checkBioStatus()
        if (bioStatus!=1) {
            super.onBackPressed()
            finish()
        }else{
            if (EKTPLoginActivityViewModel().getBiometricLogin()){
                super.onBackPressed()
                finish()
            }else{
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.loginNavHostFragment,EKTPLoginBiometricLoginFragment())
                    commit()
                }
            }
        }
    }
    //---
}