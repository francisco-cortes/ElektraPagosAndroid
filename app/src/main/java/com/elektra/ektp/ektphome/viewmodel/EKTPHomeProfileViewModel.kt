package com.elektra.ektp.ektphome.viewmodel

import androidx.lifecycle.ViewModel
import com.elektra.ektp.ektpsharedpreferences.EKTPUserApplication
import com.elektra.ektp.ektpsharedpreferences.EKTPUserApplication.Companion.preferences

class EKTPHomeProfileViewModel : ViewModel(){

    fun getUserData() : ArrayList<String>
    {
        val userData = arrayListOf<String>()
        userData.add(preferences.getNameUser())
        userData.add(preferences.getPaternalUser())
        userData.add(preferences.getPaternalUser())
        userData.add(preferences.getPhoneUser())
        userData.add(preferences.getEmailUser())
        return userData
    }

}