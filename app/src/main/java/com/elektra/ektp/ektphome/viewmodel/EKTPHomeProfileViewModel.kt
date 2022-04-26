package com.elektra.ektp.ektphome.viewmodel

import androidx.lifecycle.ViewModel
import com.elektra.ektp.ektpsharedpreferences.EKTPUserApplication.Companion.preferences

class EKTPHomeProfileViewModel : ViewModel(){
    //get user data from shared preferences
    fun getUserData() : ArrayList<String>
    {
        val userData = arrayListOf<String>()
        userData.add(preferences.getPhoneUser())
        userData.add(preferences.getEmailUser())
        return userData
    }

    fun getFullName () : String{
        return preferences.getNameUser() + " " + preferences.getPaternalUser() + " " + preferences.getMaternalUser()
    }
    //---
}