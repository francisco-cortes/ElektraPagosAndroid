package com.elektra.ektp.ektplogin.viewmodel

import androidx.lifecycle.ViewModel
import com.elektra.ektp.ektpsharedpreferences.EKTPUserApplication

class EKTPLoginActivityViewModel : ViewModel() {

    fun saveBiometricStatus(bioStatus: Int, bioType: Int){
        EKTPUserApplication.preferences.saveBioStatus(bioStatus)
        EKTPUserApplication.preferences.saveBioType(bioType)
    }

}