package com.elektra.ektp.ektplogin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.elektra.ektp.R
import com.elektra.ektp.databinding.ActivityLoginBinding
import com.elektra.ektp.ektputilies.ektpbiometricutil.EKTPBiometricUtil
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

        if (bioChecker.checkBioStatus()==1 && viewModel.getBioLoginActivated() ){
            if(bioChecker.determineBio()== 1 || bioChecker.determineBio() == 3){
                //viewModel.setBiometricLogin(true)//variable used for navigation
                openFragment(EKTPLoginBiometricLoginFragment())//change the fragmen
            }
        } else {
            //viewModel.setBiometricLogin(false)//variable use for navigaqtion
            openFragment(EKTPLoginPassLoginFragment())//cange the fragment
        }
        viewModel.saveBiometricStatus(bioChecker.checkBioStatus(),bioChecker.determineBio())//save the biometric status trough activity viewmodel
    }
    //---

    //function made for open fragments
    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            add(R.id.loginNavHostFragment, fragment)
            commit()
        }
    }
    //---

    //define for back button behavior
    /*override fun onBackPressed() {
        if (bioChecker.checkBioStatus()!=1 && !viewModel.getBioLoginActivated()) { //if the biometric isn´t ok it open pass login and must be only in that view
            super.onBackPressed()
            finish()
        }else{//if the biometric is ok it will open the biometric login and this is the first view for close the app must be here
            if (viewModel.getBiometricLogin()){
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
    //---*/
}