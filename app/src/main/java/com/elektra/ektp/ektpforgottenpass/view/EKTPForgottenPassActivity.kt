package com.elektra.ektp.ektpforgottenpass.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.elektra.ektp.R
import com.elektra.ektp.databinding.ActivityEktpforgottenPassBinding

class EKTPForgottenPassActivity : AppCompatActivity() {

    //Global data
    private lateinit var binding: ActivityEktpforgottenPassBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Inflate layout activity container for fragments
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ektpforgotten_pass)
    }
}