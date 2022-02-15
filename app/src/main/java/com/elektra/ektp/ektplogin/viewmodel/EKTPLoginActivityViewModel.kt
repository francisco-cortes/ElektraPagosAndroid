package com.elektra.ektp.ektplogin.viewmodel

import androidx.lifecycle.ViewModel
import com.elektra.ektp.ektpsharedpreferences.EKTPUserApplication.Companion.preferences

class EKTPLoginActivityViewModel : ViewModel() {
    //function used for saving biometric info in shared preferences
    fun saveBiometricStatus(bioStatus: Int, bioType: Int){
        preferences.saveBioStatus(bioStatus)
        preferences.saveBioType(bioType)
    }
    //---
    //used like a flag to know if the user is in biometric login view
    fun setBiometricLogin (bio: Boolean){
        preferences.saveLoginWithBio(bio)
    }
    fun getBiometricLogin(): Boolean{
        return preferences.getLoginWithBio()
    }
    //---
}