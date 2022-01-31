package com.elektra.ektp.ektphome.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.elektra.ektp.R
import com.elektra.ektp.databinding.ActivityEktphomeSettingsHelpBinding

class EKTPHomeSettingsHelpActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEktphomeSettingsHelpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityEktphomeSettingsHelpBinding>(this,R.layout.activity_ektphome_settings_help)
    }
}