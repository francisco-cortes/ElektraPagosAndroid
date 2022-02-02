package com.elektra.ektp.ektphome.viewmodel

import androidx.lifecycle.ViewModel
import com.elektra.ektp.ektpsharedpreferences.EKPTUserApplication

class EKTPHomeMainViewModel : ViewModel() {

    fun getUserHomeMain() : String{
        val username = EKPTUserApplication.preferences.getNameUser()
        return username
    }

}