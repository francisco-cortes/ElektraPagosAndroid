package com.elektra.ektp.ektplogin.viewmodel

import androidx.lifecycle.ViewModel
import com.elektra.ektp.ektpsharedpreferences.EKTPUserApplication.Companion.preferences

class EKTPLoginPassLoginViewModel : ViewModel() {
    //get necessary data from shared preferences from
    fun getSavedDataLogin() : ArrayList<String>{
        val loginData = arrayListOf<String>()

        loginData.add(preferences.getBioStatus().toString())
        loginData.add(preferences.getBioType().toString())
        loginData.add(preferences.getNameUser())
        loginData.add(preferences.getTemporalPassword())
        loginData.add(preferences.getTemporalLocked().toString())

        return  loginData
    }

    fun saveLockedStatus(isLock: Boolean){
        preferences.saveTemporalLocked(isLock)
    }
}