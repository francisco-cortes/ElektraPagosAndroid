package com.elektra.ektp.ektpmovements.view

import android.annotation.SuppressLint
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
import com.elektra.ektp.R
import com.elektra.ektp.databinding.ActivityEktpMovementsDetailsBinding
import com.elektra.ektp.ektpmovements.model.EKTPMovementsModel
import java.io.File
import java.io.FileOutputStream

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

        binding.shareButtonDetailsCardView.setOnClickListener {
            val bitMap = getScreenShot(binding.detailsCardViewToShare)
            shareImage(bitMap)
            Toast.makeText(this, "Compartir detalles de movimiento", Toast.LENGTH_SHORT)
                .show()
        }
        
    }

    @SuppressLint("SetWorldReadable")
    private fun shareImage(bitMap: Bitmap?) {
        try {
            val mediaImage = File(this.externalCacheDir, "movementDetails.png")
            val fOut = FileOutputStream(mediaImage)
            bitMap?.compress(Bitmap.CompressFormat.PNG, 100, fOut)
            fOut.flush()
            fOut.close()
            mediaImage.setReadable(true, false)
            val intent = Intent(Intent.ACTION_SEND)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            val uriIntent = FileProvider.getUriForFile(this, "com.inteligenciadedatoseinovacion.elektrapagos", mediaImage)
            intent.putExtra(Intent.EXTRA_STREAM, uriIntent)
            intent.type = "image/png"
            startActivity(Intent.createChooser(intent, "Compartir imagen vía"))
        }
        catch (e: Exception){
            Log.e("FGF", "Error al compartir imagen: " + e.message)
        }
    }

    private fun getScreenShot(view: View): Bitmap?{
        var screenshot: Bitmap? = null
        try {
            val totalH = binding.detailsCardViewToShare.getChildAt(0).height
            screenshot = Bitmap.createBitmap(view.measuredWidth, totalH, Bitmap.Config.ARGB_8888)
            // Now draw this bitmap on a canvas
            val canvas = Canvas(screenshot)
            canvas.drawColor(Color.WHITE)
            view.draw(canvas)
        } catch (e: Exception) {
            Log.e("GFG", "Error al generar imagen: " + e.message)
        }
        // return the bitmap
        return screenshot
    }
}