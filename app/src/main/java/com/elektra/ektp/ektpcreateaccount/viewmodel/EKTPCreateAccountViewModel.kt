package com.elektra.ektp.ektpcreateaccount.viewmodel

import android.R
import android.content.Context
import androidx.lifecycle.ViewModel
import com.elektra.ektp.ektpsharedpreferences.EKPTUserApplication

class EKTPCreateAccountViewModel(): ViewModel() {

    fun saveRegisterData(
        nameUser: String, paternalLast: String, maternalLast: String, birthDate: String, birthState: String,
        phone: String, eMailText: String, gender: String
    ){
        EKPTUserApplication.preferences.saveNameUser(nameUser)
        EKPTUserApplication.preferences.savePaternalUser(paternalLast)
        EKPTUserApplication.preferences.saveMaternalUser(maternalLast)
        EKPTUserApplication.preferences.saveBirthDateUser(birthDate)
        EKPTUserApplication.preferences.saveBirthStateUser(birthState)
        EKPTUserApplication.preferences.saveGenderUser(gender)
        EKPTUserApplication.preferences.saveEmailUser(eMailText)
        EKPTUserApplication.preferences.savePhoneUser(phone)
    }

}