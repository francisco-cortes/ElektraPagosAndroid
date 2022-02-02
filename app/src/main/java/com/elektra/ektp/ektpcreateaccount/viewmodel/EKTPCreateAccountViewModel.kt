package com.elektra.ektp.ektpcreateaccount.viewmodel

import androidx.lifecycle.ViewModel
import com.elektra.ektp.ektpsharedpreferences.EKTPUserApplication
import com.elektra.ektp.ektpsharedpreferences.EKTPUserApplication.Companion.preferences

class EKTPCreateAccountViewModel(): ViewModel() {

    fun saveRegisterData(
        nameUser: String, paternalLast: String, maternalLast: String, birthDate: String, birthState: String,
        phone: String, eMailText: String, gender: String
    ){
        preferences.saveNameUser(nameUser)
        preferences.savePaternalUser(paternalLast)
        preferences.saveMaternalUser(maternalLast)
        preferences.saveBirthDateUser(birthDate)
        preferences.saveBirthStateUser(birthState)
        preferences.saveGenderUser(gender)
        preferences.saveEmailUser(eMailText)
        preferences.savePhoneUser(phone)
    }

}