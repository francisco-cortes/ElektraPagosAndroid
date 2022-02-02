package com.elektra.ektp.ektphome.viewmodel

import androidx.lifecycle.ViewModel
import com.elektra.ektp.ektpsharedpreferences.EKPTUserApplication

class EKTPHomeProfileViewModel : ViewModel(){

    fun getUserData() : ArrayList<String>
    {
        val userData = arrayListOf<String>()
        userData.add(EKPTUserApplication.preferences.getNameUser())
        return userData
    }

}