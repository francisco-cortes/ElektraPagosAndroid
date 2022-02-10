package com.elektra.ektp.ektpbiometricutil

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.biometric.BiometricManager

class EKTPBiometricUtil(private val context: Context) {

    //requiere el contexto donde sea llamado
    fun checkBioStatus(): Int {
        val biometricManager = BiometricManager.from(context)
        return when (biometricManager.canAuthenticate()) {
            BiometricManager.BIOMETRIC_SUCCESS ->
                // Se pueden usar los biometricos
                1
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE ->
                // No hay hardware en el dispsitivo
                2
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE ->
                // Hay hardware pero no esta disponible
                3
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED ->
                // Hay hardware pero el usuario no esta erollado con ellos
                4
            else -> 2
        }
    }

    //requiere el contexto donde es llamado
    fun determineBio() : Int {

        var packageManager: PackageManager = context.getPackageManager()
        var bioUsed = 0

        //determinasmos la version de android con el packegemanager
        val androidVersion = when(Build.VERSION.SDK_INT){
            in 0..23 -> 0 // 0 a 23 no soportan el uso de biometricos
            in 24..28 -> 1 //24 a 28 solo soportan el uso de huella
            else -> 2 // 29 en adelante soportan el uso de face, iris y finger
        }

        if (androidVersion==2){
            bioUsed = if (packageManager.hasSystemFeature(PackageManager.FEATURE_FACE)){
                1
            } else if (packageManager.hasSystemFeature(PackageManager.FEATURE_IRIS)){
                2
            } else if (packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)){
                3
            } else{
                0
            }
        }else if (androidVersion == 1){
            bioUsed = 3
        }else{
            bioUsed = 0
        }

        return bioUsed
        // 3 = finger 2 = iris, 1 = face y 0 = ninguno
    }

}