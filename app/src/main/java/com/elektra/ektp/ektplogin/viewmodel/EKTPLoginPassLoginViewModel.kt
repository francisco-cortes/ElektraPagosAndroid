package com.elektra.ektp.ektplogin.viewmodel

import androidx.lifecycle.ViewModel
import com.elektra.ektp.ektpsharedpreferences.EKPTUserApplication

class EKTPLoginPassLoginViewModel : ViewModel() {

    fun getSavedDataLogin() : ArrayList<String>{
        val loginData = arrayListOf<String>()

        loginData.add(EKPTUserApplication.preferences.getBioStatus().toString())
        loginData.add(EKPTUserApplication.preferences.getBioType().toString())
        loginData.add(EKPTUserApplication.preferences.getEncryptToken())

        return  loginData
    }
}