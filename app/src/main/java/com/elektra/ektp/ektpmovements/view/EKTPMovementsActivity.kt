package com.elektra.ektp.ektpmovements.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.elektra.ektp.R
import com.elektra.ektp.databinding.ActivityEktpMovementsBinding

class EKTPMovementsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEktpMovementsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ektp_movements)
    }
}