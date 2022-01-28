package com.elektra.ektp.ektphome.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.elektra.ektp.R
import com.elektra.ektp.databinding.ActivityLoginBinding

class EKTPHomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityLoginBinding>(this,R.layout.activity_ektphome)
    }

    private fun openFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.homeNavHostFragment, fragment)
            commit()
        }
    }
}