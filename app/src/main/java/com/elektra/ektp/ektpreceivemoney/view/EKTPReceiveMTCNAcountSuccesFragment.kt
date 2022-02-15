package com.elektra.ektp.ektpreceivemoney.view

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
import androidx.core.content.FileProvider
import androidx.databinding.DataBindingUtil
import com.elektra.ektp.R
import com.elektra.ektp.databinding.FragmentReceiveMtcnAcountSuccesBinding
import java.io.File
import java.io.FileOutputStream

class EKTPReceiveMTCNAcountSuccesFragment : Fragment() {

    private lateinit var binding: FragmentReceiveMtcnAcountSuccesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  DataBindingUtil.inflate<FragmentReceiveMtcnAcountSuccesBinding>(inflater,R.layout.fragment_receive_mtcn_acount_succes, container, false)
        binding.doneButton.setOnClickListener {
            activity?.finish()
        }

        binding.shareDetailsButton.setOnClickListener{
            view: View ->
            Toast.makeText(activity, "Compartiendo detalles de movimiento", Toast.LENGTH_SHORT)
                .show()
            val bitMap = getScreenShot(binding.detailsCardViewToShare)
            shareImage(bitMap)

        }

        return binding.root
    }

    @SuppressLint("SetWorldReadable")
    private fun shareImage(bitMap: Bitmap?) {
        try {
            val mediaImage = File(activity?.externalCacheDir, "movementDetails.png")
            val fOut = FileOutputStream(mediaImage)
            bitMap?.compress(Bitmap.CompressFormat.PNG, 100, fOut)
            fOut.flush()
            fOut.close()
            mediaImage.setReadable(true, false)
            val intent = Intent(Intent.ACTION_SEND)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            val uriIntent = FileProvider.getUriForFile(activity as Context, "com.elektra.ektp", mediaImage)
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