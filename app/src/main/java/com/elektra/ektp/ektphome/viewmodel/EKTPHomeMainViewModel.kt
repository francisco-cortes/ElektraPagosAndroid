package com.elektra.ektp.ektphome.viewmodel

import androidx.lifecycle.ViewModel
import com.elektra.ektp.ektpsharedpreferences.EKTPUserApplication

class EKTPHomeMainViewModel : ViewModel() {
    //get user name trough shared preferences
    fun getUserHomeMain() : String{
        val username = EKTPUserApplication.preferences.getNameUser()
        return username
    }
    //---
}