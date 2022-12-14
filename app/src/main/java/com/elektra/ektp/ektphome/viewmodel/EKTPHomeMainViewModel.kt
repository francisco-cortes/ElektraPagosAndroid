package com.elektra.ektp.ektphome.viewmodel

import androidx.lifecycle.ViewModel
import com.elektra.ektp.ektpsharedpreferences.EKTPUserApplication

class EKTPHomeMainViewModel : ViewModel() {
    //get user name trough shared preferences
    fun getUserHomeMain(): String {
        return EKTPUserApplication.preferences.getNameUser()
    }
    //---
}