package com.elektra.ektp.ektplogin.viewmodel

import androidx.lifecycle.ViewModel
import com.elektra.ektp.ektpsharedpreferences.EKTPUserApplication
import com.elektra.ektp.ektpsharedpreferences.EKTPUserApplication.Companion.preferences

class EKTPLoginActivityViewModel : ViewModel() {

    fun saveBiometricStatus(bioStatus: Int, bioType: Int){
        preferences.saveBioStatus(bioStatus)
        preferences.saveBioType(bioType)
    }

}