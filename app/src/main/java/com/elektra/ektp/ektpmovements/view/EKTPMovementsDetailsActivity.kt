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

    //Global databinding access variable
    private lateinit var binding: ActivityEktpMovementsDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Layout inflater
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ektp_movements_details)

        //Recovery intent
        val intent = intent
        //Data recovery from intent extras
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
        //---

        //Assignment from intent data into layout
        binding.movementsDetailsItem = dataList

        //OnClickListener for call shareDetails from card view
        /*binding.shareButtonDetailsCardView.setOnClickListener {
            //Toast message indicates to user that details will be shared
            Toast.makeText(this, "Compartiendo detalles de movimiento", Toast.LENGTH_SHORT)
                .show()
            //Get value screenshot from details to share
            val bitMap = getScreenShot(binding.detailsCardViewToShare)
            //Invocation intent to share screenshot
            shareImage(bitMap)
        }*/
        //---

        //OnClickListener for destroy activity and back to earlier activity
        binding.backAppbarButton.setOnClickListener {
            onBackPressed()
        }
        //---
        
    }

    //Function to share details image through intent that returns, needs bitmap entry
    @SuppressLint("SetWorldReadable")
    private fun shareImage(bitMap: Bitmap?) {
        try {
            //Gets de bitmap file from cache view
            val mediaImage = File(this.externalCacheDir, "movementDetails.png")
            //Gets the output view from cache
            val fOut = FileOutputStream(mediaImage)
            //Compression method for bitmap (png format, quality 100 and file "fout")
            bitMap?.compress(Bitmap.CompressFormat.PNG, 100, fOut)
            //Flush the cache
            fOut.flush()
            //Clear cache
            fOut.close()
            //Set permission for read
            mediaImage.setReadable(true, false)
            //Invoke intent "action send type"
            val intent = Intent(Intent.ACTION_SEND)
            //Set flag to launch action send
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            //Set file provider from ekpt
            val uriIntent = FileProvider.getUriForFile(this, "com.elektra.ektp", mediaImage)
            //set intent data to send
            intent.putExtra(Intent.EXTRA_STREAM, uriIntent)
            //set the data type to intent
            intent.type = "image/png"
            //launch intent
            startActivity(Intent.createChooser(intent, "Compartir imagen vía"))
        }
        //Exception call
        catch (e: Exception){
            Log.e("FGF", "Error al compartir imagen: " + e.message)
        }
        //---
    }
    //---

    //Function to get screenshot from view binding that returns bitmap, needs a view entry
    private fun getScreenShot(view: View): Bitmap?{
        //Set a variable bitmap type as null
        var screenshot: Bitmap? = null
        try {
            //Get the total height from view, "cardview" in this case
            val totalH = binding.detailsCardViewToShare.getChildAt(0).height
            //Create a bitmap frame with width and height as cardview
            screenshot = Bitmap.createBitmap(view.measuredWidth, totalH, Bitmap.Config.ARGB_8888)
            // Now draw this bitmap on a canvas
            val canvas = Canvas(screenshot)
            //Set canvas background as white
            canvas.drawColor(Color.WHITE)
            //Draw the view on canvas frame
            view.draw(canvas)
        }
        //Exception call
        catch (e: Exception) {
            Log.e("GFG", "Error al generar imagen: " + e.message)
        }
        //---
        // return the bitmap
        return screenshot
    }
    //---

    //OnBackPressed to destroy activity
    override fun onBackPressed(){
        super.onBackPressed()
        finish()
    }
    //---
}