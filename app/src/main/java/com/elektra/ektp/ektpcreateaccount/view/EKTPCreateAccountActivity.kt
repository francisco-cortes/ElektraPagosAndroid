package com.elektra.ektp.ektpcreateaccount.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.elektra.ektp.R
import com.elektra.ektp.databinding.ActivityCreateAccountBinding
import com.elektra.ektp.ektpsharedpreferences.EKTPUserApplication.Companion.preferences

class EKTPCreateAccountActivity : AppCompatActivity() {
    //Global variables
    private lateinit var binding: ActivityCreateAccountBinding
    //---
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Inflate layout activity container for fragments
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_account)
        when(preferences.getLocalStatus()){
            "preRegistrado" -> openFragment(EKTPCreateAccountSMSVerificationFragment())
            "Registrado" -> openFragment(EKTPCreateAccountSMSVerificationFragment())
            "clienteActivado" -> openFragment(EKTPCreateAccountSMSVerificationFragment())
            "clienteBancarizado" -> finish()
            else -> openFragment(EKTPCreateAccountFragment())
        }
    }
    //function made for open fragments
    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            add(R.id.CreateAccountNavigatorHost, fragment)
            commit()
        }
    }
    //---
}