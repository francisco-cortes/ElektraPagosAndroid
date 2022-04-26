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
import androidx.core.content.FileProvider
import androidx.core.view.isGone
import androidx.databinding.DataBindingUtil
import com.elektra.ektp.R
import com.elektra.ektp.databinding.FragmentEktpReceiveMtcnShareImageBinding
import com.elektra.ektp.ektputilies.ektptoaster.EKTPToaster
import java.io.File
import java.io.FileOutputStream

class EKTPReceiveMTCNShareImageFragment : Fragment() {

    private lateinit var binding: FragmentEktpReceiveMtcnShareImageBinding

    private val toaster = EKTPToaster()
    private var mtcnString = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        val bundle = this.arguments

        mtcnString = bundle?.getString("mtcnString").toString()

        binding =  DataBindingUtil.inflate(inflater,R.layout.fragment_ektp_receive_mtcn_share_image, container, false)

        binding.naDetailCardView.text = mtcnString

        binding.shareThisButton.setOnClickListener {
            activity?.let { it1 -> toaster.makeAToast(it1,"Compartiendo Detalles de movimiento") }
            binding.shareThisButton.isGone = true
            val bitMap = getScreenShot(binding.detailsCardViewToShare)
            shareImage(bitMap)
            binding.shareThisButton.isGone = false
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