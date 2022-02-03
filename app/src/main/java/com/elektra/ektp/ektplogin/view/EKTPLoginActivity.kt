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

    private lateinit var binding: ActivityLoginBinding

    private var bioStatus by Delegates.notNull<Int>()
    private var bioUsed by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityLoginBinding>(this,R.layout.activity_login)
        bioStatus = EKTPBiometricUtil().checkBioStatus(this)
        bioUsed = EKTPBiometricUtil().determineBio(this)

        if (bioStatus==1){
            if(bioUsed == 1 || bioUsed == 3){
                EKTPLoginActivityViewModel().setBiometricLogin(true)
                openFragment(EKTPLoginBiometricLoginFragment())
            }
        } else {
            openFragment(EKTPLoginPassLoginFragment())
            EKTPLoginActivityViewModel().setBiometricLogin(false)
        }
        EKTPLoginActivityViewModel().saveBiometricStatus(bioStatus,bioUsed)
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            add(R.id.loginNavHostFragment, fragment)
            commit()
        }
    }

    override fun onBackPressed() {
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

}