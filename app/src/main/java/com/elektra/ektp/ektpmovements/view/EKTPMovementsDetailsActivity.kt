package com.elektra.ektp.ektpmovements.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.elektra.ektp.R
import com.elektra.ektp.databinding.ActivityEktpMovementsDetailsBinding
import com.elektra.ektp.ektpmovements.model.EKTPMovementsModel

class EKTPMovementsDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEktpMovementsDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ektp_movements_details)

        val intent = intent
        val moveName = intent.getStringExtra ("detailName").toString()
        val moveDate = intent.getStringExtra ("detailDate").toString()
        val moveTitle = intent.getStringExtra ("detailTitle").toString()
        val moveConcept = intent.getStringExtra ("detailConcept").toString()
        val moveAmount = intent.getStringExtra ("detailAmount").toString()
        val moveStatus = intent.getStringExtra ("detailStatus").toString()
        val moveFolio = intent.getStringExtra ("detailFolio").toString()
        val moveAccount = intent.getStringExtra ("detailAccount").toString()
        val dataList = EKTPMovementsModel(
            moveAmount,
            moveTitle,
            moveDate,
            moveAccount,
            moveName,
            moveConcept,
            moveFolio,
            moveStatus
        )

        binding.movementsDetailsItem = dataList
        
    }
}