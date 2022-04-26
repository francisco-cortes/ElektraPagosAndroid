package com.elektra.ektp.ektpsplashactivity.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import com.elektra.ektp.R
import com.elektra.ektp.databinding.ActivitySplashBinding
import com.elektra.ektp.ektplogin.view.EKTPLoginActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val actualVersion = "V 1.0" // write here the current version for this app
    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        binding.actualVersionTextView.text = actualVersion

        //to show the activity in full-screen mode used only for the splash
        window.decorView.apply {
            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        }
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        //--
        //this use a delay to close the activity and open a new one
        Handler().postDelayed({
            val intent = Intent(this, EKTPLoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000) // delay is in milliseconds trough this function.
        //---
    }
}