package com.elektra.ektp.ektppdfviewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.elektra.ektp.R
import com.elektra.ektp.databinding.ActivityEktppdfviewerBinding

class EKTPPDFViewerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEktppdfviewerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val selectedContract = intent.getStringExtra("selected_contract")
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ektppdfviewer)

        binding.pdfView.fromAsset(selectedContract).enableSwipe(true).password(null).nightMode(false).load()

        binding.backAppbarButton.setOnClickListener {
            onBackPressed()
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}