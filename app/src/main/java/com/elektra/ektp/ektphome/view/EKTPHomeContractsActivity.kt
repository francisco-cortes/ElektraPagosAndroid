package com.elektra.ektp.ektphome.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.elektra.ektp.R
import com.elektra.ektp.databinding.ActivityEktpHomeContractsBinding
import com.elektra.ektp.ektppdfviewer.EKTPPDFViewerActivity

class EKTPHomeContractsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEktpHomeContractsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ektp_home_contracts)

        with(binding){
            termsCaptationTextView.setOnClickListener {
                intentOpener("ejemplo_contrato_banca_digital.pdf")
            }

            bankContractTextView.setOnClickListener {
                intentOpener("ejemplo_contrato_banca_digital.pdf")
            }

            contractsLabelTextView.setOnClickListener {
                intentOpener("ejemplo_contrato_banca_digital.pdf")
            }

            creditBureauTextView.setOnClickListener {
                intentOpener("ejemplo_contrato_banca_digital.pdf")
            }

            privacityWarnTextView.setOnClickListener {
                intentOpener("ejemplo_contrato_banca_digital.pdf")
            }

            termsCaptationProductsTextView.setOnClickListener {
                intentOpener("ejemplo_contrato_banca_digital.pdf")
            }

            termsConditionsTextView.setOnClickListener {
                intentOpener("ejemplo_contrato_banca_digital.pdf")
            }

            termsCredimaxTextView.setOnClickListener {
                intentOpener("ejemplo_contrato_banca_digital.pdf")
            }

            termsDigitalTextView.setOnClickListener {
                intentOpener("contratoenPDF.pdf")
            }

            backAppbarButton.setOnClickListener {
                onBackPressed()
            }
        }
    }
    private fun intentOpener(selectedContract: String){
        val intent = Intent(this, EKTPPDFViewerActivity::class.java)
        intent.putExtra("selected_contract", selectedContract)
        startActivity(intent)
    }
}