package com.elektra.ektp.ektphome.viewmodel

import androidx.lifecycle.ViewModel
import com.elektra.ektp.ektpsharedpreferences.EKTPUserApplication

class EKTPHomeProfileViewModel : ViewModel(){

    fun getUserData() : ArrayList<String>
    {
        val userData = arrayListOf<String>()
        userData.add(EKTPUserApplication.preferences.getNameUser())
        userData.add(EKTPUserApplication.preferences.getPhoneUser())
        userData.add(EKTPUserApplication.preferences.getEmailUser())
        return userData
    }

}