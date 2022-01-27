package com.elektra.ektp.ektpbiometricutil

import android.content.Context
import androidx.biometric.BiometricManager

class EKTPBiometricUtil {

    fun checkBioStatus(context: Context): Int {
        val biometricManager = BiometricManager.from(context)
        return when (biometricManager.canAuthenticate()) {
            BiometricManager.BIOMETRIC_SUCCESS ->
                // App can authenticate using biometrics
                1
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE ->
                // No biometric features available on this device
                2
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE ->
                // Biometric features are currently unavailable
                3
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED ->
                4
            // The user hasn't associated any biometric credentials with their account
            else -> 0
        }
    }

}