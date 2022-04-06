package com.elektra.ektp.ektplogin.viewmodel

import androidx.lifecycle.ViewModel
import com.elektra.ektp.ektpsharedpreferences.EKTPUserApplication.Companion.preferences
class EKTPLoginBiometricLoginViewModel : ViewModel() {
    //get data from shared preferences as an array
    fun getSavedDataLogin() : ArrayList<String>{
        val loginData = arrayListOf<String>()

        loginData.add(preferences.getBioStatus().toString())
        loginData.add(preferences.getBioType().toString())
        loginData.add(preferences.getNameUser())
        loginData.add(preferences.getTemporalPassword())

        return  loginData
    }
    //---
}