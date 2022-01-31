package com.elektra.ektp.ektphome.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.elektra.ektp.R
import com.elektra.ektp.databinding.ActivityEktphomeBinding
import com.elektra.ektp.databinding.ActivityLoginBinding

class EKTPHomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEktphomeBinding

    private var isHome = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityEktphomeBinding>(this,R.layout.activity_ektphome)

        openFragment(EKTPHomeMainFragment())

        binding.navbar.setOnNavigationItemSelectedListener { menuItem ->
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
    }

    private fun openFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.homeNavHostFragment, fragment)
            commit()
        }
    }
}