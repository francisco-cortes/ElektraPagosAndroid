package com.elektra.ektp.ektpcreateaccount.viewmodel

import androidx.lifecycle.ViewModel
import com.elektra.ektp.ektpsharedpreferences.EKTPUserApplication.Companion.preferences

class EKTPCReateAccountBiometricsActivationViewModel : ViewModel() {
    fun saveBioInLogin(bioIsActivated : Boolean){
        preferences.saveBioLogin(bioIsActivated)
    }
}