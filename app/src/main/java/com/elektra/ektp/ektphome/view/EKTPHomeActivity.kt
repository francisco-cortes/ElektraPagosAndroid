package com.elektra.ektp.ektphome.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.elektra.ektp.R
import com.elektra.ektp.databinding.ActivityEktphomeBinding
import com.elektra.ektp.databinding.ActivityLoginBinding
import com.elektra.ektp.ektplogin.view.EKTPLoginActivity

class EKTPHomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEktphomeBinding

    private var isHome = true //used for navBar behaviour

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //inflate for this activity
        binding = DataBindingUtil.setContentView<ActivityEktphomeBinding>(this,R.layout.activity_ektphome)

        openFragment(EKTPHomeMainFragment())//open the home main fragment

        //button navigation bar controller
        binding.navbar.setOnItemSelectedListener{ menuItem ->
            when (menuItem.itemId){
                R.id.startButton->{
                    openFragment(EKTPHomeMainFragment())
                    isHome = true
                    true
                }
                R.id.profileButton->{
                    openFragment(EKTPHomeProfileFragment())
                    isHome = false
                    true
                }
                R.id.noticeButton->{
                    openFragment(EKTPHomeNoticeFragment())
                    isHome = false
                    true
                }
                R.id.settingsButton->{
                    openFragment(EKTPHomeSettingsFragment())
                    isHome = false
                    true
                }
                else -> false
            }
        }
        //---
    }

    //back button behaviour
    override fun onBackPressed() {
        if (isHome) { // the home variable tell us if the user is in the first fragment
            super.onBackPressed()
            openActivity(EKTPLoginActivity())
            finish()// close the home activity
        } else {
            openFragment(EKTPHomeMainFragment())// return to first fragment
            binding.navbar.menu.findItem(R.id.startButton).setChecked(true)
            isHome = true
        }
    }

    //function to open a fragment
    private fun openFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.homeNavHostFragment, fragment)
            commit()
        }
    }

    private fun openActivity(activityName: Activity){
        val intent = Intent(this, activityName::class.java)
        startActivity(intent)
    }
}