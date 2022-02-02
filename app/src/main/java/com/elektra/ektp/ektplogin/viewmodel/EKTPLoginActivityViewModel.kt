package com.elektra.ektp.ektplogin.viewmodel

import androidx.lifecycle.ViewModel
import com.elektra.ektp.ektpsharedpreferences.EKTPUserApplication

class EKTPLoginActivityViewModel : ViewModel() {

    fun saveBiometricStatus(bioStatus: Int, bioType: Int){
        EKTPUserApplication.preferences.saveBioStatus(bioStatus)
        EKTPUserApplication.preferences.saveBioType(bioType)
    }

    fun userIsEnrolled () : Boolean {
        val isEnrolled = false
        val name = EKTPUserApplication.preferences.getNameUser()

        if (name != null || name != "") isEnrolled == false

        return isEnrolled
    }
}