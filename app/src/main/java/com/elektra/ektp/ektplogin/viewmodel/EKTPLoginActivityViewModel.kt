package com.elektra.ektp.ektplogin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.elektra.ektp.ektpsharedpreferences.EKPTUserApplication

class EKTPLoginActivityViewModel : ViewModel() {

    fun saveBiometricStatus(bioStatus: Int, bioType: Int){
        EKPTUserApplication.preferences.saveBioStatus(bioStatus)
        EKPTUserApplication.preferences.saveBioType(bioType)
    }

}