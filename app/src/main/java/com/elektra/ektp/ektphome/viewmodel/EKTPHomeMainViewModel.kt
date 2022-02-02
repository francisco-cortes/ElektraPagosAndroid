package com.elektra.ektp.ektphome.viewmodel

import androidx.lifecycle.ViewModel
import com.elektra.ektp.ektpsharedpreferences.EKTPUserApplication

class EKTPHomeMainViewModel : ViewModel() {

    fun getUserHomeMain() : String{
        val username = EKTPUserApplication.preferences.getNameUser()
        return username
    }

}