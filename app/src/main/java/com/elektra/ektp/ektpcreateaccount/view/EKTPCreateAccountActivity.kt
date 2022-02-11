package com.elektra.ektp.ektpcreateaccount.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.elektra.ektp.R
import com.elektra.ektp.databinding.ActivityCreateAccountBinding

class EKTPCreateAccountActivity : AppCompatActivity() {
    //Global variables
    private lateinit var binding: ActivityCreateAccountBinding
    //---
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Inflate layout activity container for fragments
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_account)
    }
}