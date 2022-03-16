package com.elektra.ektp.ektpmovements.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.content.FileProvider
import androidx.core.view.isGone
import androidx.navigation.fragment.findNavController
import com.elektra.ektp.databinding.FragmentEktpDetailsToShareBinding
import com.elektra.ektp.ektpmovements.model.EKTPMovementsModel
import java.io.File
import java.io.FileOutputStream

class EKTPDetailsToShareFragment : Fragment() {

    private lateinit var binding: FragmentEktpDetailsToShareBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Overriding obBackPressed to popBackStack fragment
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEktpDetailsToShareBinding.inflate(inflater, container, false)

        val bundle = this.arguments
        //Data recovery from intent extras
        val moveAmount = bundle?.getString("detailAmount").toString()
        val moveTitle = bundle?.getString("detailTitle").toString()
        val moveDate = bundle?.getString("detailDate").toString()
        val moveAccount = bundle?.getString("detailAccount").toString()
        val moveReceivedName = bundle?.getString("detailReceivedName").toString()
        val moveConcept = bundle?.getString("detailConcept").toString()
        val moveFolio = bundle?.getString("detailFolio").toString()
        val moveStatus = bundle?.getString("detailStatus").toString()
        val moveMTCN = bundle?.getString("detailMTCN").toString()
        val moveOperationType = bundle?.getString("detailOperationType").toString()
        val moveWithdrewName = bundle?.getString("detailWithdrewName").toString()
        val moveCents = bundle?.getString("detailCents").toString()

        val dataList = EKTPMovementsModel(
            moveAmount,
            moveTitle,
            moveDate,
            moveAccount,
            moveReceivedName,
            moveConcept,
            moveFolio,
            moveStatus,
            moveMTCN,
            false,
            moveOperationType,
            moveWithdrewName
        )
        //---

        with(binding){
            cashWithDrawalLinearLayout.isGone = moveTitle != "Retiro de efectivo"
            payOrderLinearLayout.isGone = moveTitle != "Orden de Pago"
            receiveDepositLinearLayout.isGone = moveTitle != "Recepción de depósito"

            movementsDetailsItem = dataList
            amDetailCentsCardView.text = moveCents

            shareThisButton.setOnClickListener{
                //Toast message indicates to user that details will be shared
                Toast.makeText(activity as Context, "Compartiendo detalles de movimiento", Toast.LENGTH_SHORT)
                    .show()
                shareThisButton.isGone = true
                //Get value screenshot from details to share
                val bitMap = getScreenShot(detailsCardViewToShare)
                //Invocation intent to share screenshot
                shareImage(bitMap)
                shareThisButton.isGone = false
            }

            shareThisButton2.setOnClickListener{
                //Toast message indicates to user that details will be shared
                Toast.makeText(activity as Context, "Compartiendo detalles de movimiento", Toast.LENGTH_SHORT)
                    .show()
                shareThisButton2.isGone = true
                //Get value screenshot from details to share
                val bitMap = getScreenShot(detailsCardViewToShare)
                //Invocation intent to share screenshot
                shareImage(bitMap)
                shareThisButton2.isGone = false
            }

            shareThisButton3.setOnClickListener{
                //Toast message indicates to user that details will be shared
                Toast.makeText(activity as Context, "Compartiendo detalles de movimiento", Toast.LENGTH_SHORT)
                    .show()
                shareThisButton3.isGone = true
                //Get value screenshot from details to share
                val bitMap = getScreenShot(detailsCardViewToShare)
                //Invocation intent to share screenshot
                shareImage(bitMap)
                shareThisButton3.isGone = false
            }
        }


        return binding.root
    }

    //Function to share details image through intent that returns, needs bitmap entry
    @SuppressLint("SetWorldReadable")
    private fun shareImage(bitMap: Bitmap?) {
        val context = activity as Context
        try {
            //Gets de bitmap file from cache view
            val mediaImage = File(context.externalCacheDir, "movementDetails.png")
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
            val uriIntent = FileProvider.getUriForFile(context, "com.elektra.ektp", mediaImage)
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

}