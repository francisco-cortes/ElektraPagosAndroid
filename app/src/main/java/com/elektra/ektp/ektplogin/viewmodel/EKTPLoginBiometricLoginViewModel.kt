package com.elektra.ektp.ektplogin.viewmodel

import androidx.lifecycle.ViewModel
import com.elektra.ektp.ektpsharedpreferences.EKTPUserApplication

class EKTPLoginBiometricLoginViewModel : ViewModel() {

    fun getSavedDataLogin() : ArrayList<String>{
        val loginData = arrayListOf<String>()

        loginData.add(EKTPUserApplication.preferences.getBioStatus().toString())
        loginData.add(EKTPUserApplication.preferences.getBioType().toString())
        loginData.add(EKTPUserApplication.preferences.getEncryptToken())
        loginData.add(EKTPUserApplication.preferences.getNameUser())

        return  loginData
    }
}