package com.elektra.ektp.ektphome.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.elektra.ektp.R
import com.elektra.ektp.databinding.ActivityEktpHomeContractsBinding
import com.elektra.ektp.databinding.ActivityEktphomeBinding

class EKTPHomeContractsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEktpHomeContractsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityEktpHomeContractsBinding>(this, R.layout.activity_ektp_home_contracts)

        binding.backAppbarButton.setOnClickListener {
            onBackPressed()
        }
    }
}