package com.elektra.ektp.ektpbiometricutil

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.biometric.BiometricManager
//can instance this class with the current context
class EKTPBiometricUtil(private val context: Context) {

    //check de biometric hardware status trough biometric manager, only works in api 26 and further
    fun checkBioStatus(): Int {
        val biometricManager = BiometricManager.from(context)
        return when (biometricManager.canAuthenticate()) {
            BiometricManager.BIOMETRIC_SUCCESS ->
                // can use biometrics
                1
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE ->
                2
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE ->
                3
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED ->
                4
            else -> 2
        }
    }
    //---
    //determine the biometric type depending api level,
    fun determineBio() : Int {

        var packageManager: PackageManager = context.getPackageManager()
        var bioUsed = 0

        //determine android api on device
        val androidVersion = when(Build.VERSION.SDK_INT){
            in 0..23 -> 0 // 0 a 23 no soportan el uso de biometricos
            in 24..28 -> 1 //24 a 28 solo soportan el uso de huella
            else -> 2 // 29 en adelante soportan el uso de face, iris y finger
        }
        //depending android api gets biometric type
        bioUsed = when (androidVersion) {
            2 -> {
                when {
                    packageManager.hasSystemFeature(PackageManager.FEATURE_FACE) -> {
                        1
                    }
                    packageManager.hasSystemFeature(PackageManager.FEATURE_IRIS) -> {
                        2
                    }
                    packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT) -> {
                        3
                    }
                    else -> {
                        0
                    }
                }
            }
            1 -> {
                3
            }
            else -> {
                0
            }
        }

        return bioUsed
        // 3 = finger 2 = iris, 1 = face y 0 = none
    }
//---
}