package com.elektra.ektp.ektpreceivemoney.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.elektra.ektp.R
import com.elektra.ektp.databinding.ActivityEktpreceiveMoneyBinding

class EKTPReceiveMoneyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEktpreceiveMoneyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //inflate this layout
        binding = DataBindingUtil.setContentView(this,R.layout.activity_ektpreceive_money)
    }
}