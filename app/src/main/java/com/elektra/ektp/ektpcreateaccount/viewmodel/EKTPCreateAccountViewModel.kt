package com.elektra.ektp.ektpcreateaccount.viewmodel

import androidx.lifecycle.ViewModel
import com.elektra.ektp.ektpsharedpreferences.EKTPUserApplication

class EKTPCreateAccountViewModel(): ViewModel() {

    fun saveRegisterData(
        nameUser: String, paternalLast: String, maternalLast: String, birthDate: String, birthState: String,
        phone: String, eMailText: String, gender: String
    ){
        EKTPUserApplication.preferences.saveNameUser(nameUser)
        EKTPUserApplication.preferences.savePaternalUser(paternalLast)
        EKTPUserApplication.preferences.saveMaternalUser(maternalLast)
        EKTPUserApplication.preferences.saveBirthDateUser(birthDate)
        EKTPUserApplication.preferences.saveBirthStateUser(birthState)
        EKTPUserApplication.preferences.saveGenderUser(gender)
        EKTPUserApplication.preferences.saveEmailUser(eMailText)
        EKTPUserApplication.preferences.savePhoneUser(phone)
    }

}