package com.elektra.ektp.ektplogin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.elektra.ektp.R
import com.elektra.ektp.databinding.ActivityLoginBinding
import com.elektra.ektp.ektpbiometricutil.EKTPBiometricUtil

class EKTPLoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityLoginBinding>(this,R.layout.activity_login)

        val checkBioStatus = EKTPBiometricUtil().checkBioStatus(this)
        val bioUsed = EKTPBiometricUtil().determineBio(this)

        if (checkBioStatus == 1 && bioUsed == 1) {
            openFragment(EKTPLoginBiometricLoginFragment())
        }else if (checkBioStatus == 1 && bioUsed == 2 ){
            openFragment(EKTPLoginPassLoginFragment())
        }else if (checkBioStatus == 1 && bioUsed == 3){
            openFragment(EKTPLoginBiometricLoginFragment())
        }else{
            openFragment(EKTPLoginPassLoginFragment())
        }

    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.loginNavHostFragment, fragment)
            commit()
        }
    }
}