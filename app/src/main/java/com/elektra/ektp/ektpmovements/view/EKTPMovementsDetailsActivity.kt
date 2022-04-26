package com.elektra.ektp.ektpmovements.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.elektra.ektp.R
import com.elektra.ektp.databinding.ActivityEktpMovementsDetailsBinding

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