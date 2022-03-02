package com.elektra.ektp.ektpmovements.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.elektra.ektp.R
import com.elektra.ektp.databinding.ActivityEktpMovementsDetailsBinding
import com.elektra.ektp.ektpmovements.model.EKTPMovementsModel
import java.io.File
import java.io.FileOutputStream

class EKTPMovementsDetailsActivity : AppCompatActivity() {

    //Global databinding access variable
    private lateinit var binding: ActivityEktpMovementsDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Layout inflater
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ektp_movements_details)

        val navController  by lazy {
            findNavController(R.id.movementsDetailsNavigatorHost)
        }
        val intent = intent
        navController.setGraph(R.navigation.movements_navigator, intent.extras)
    }
}