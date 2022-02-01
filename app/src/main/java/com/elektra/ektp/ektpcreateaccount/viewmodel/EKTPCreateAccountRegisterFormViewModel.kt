package com.elektra.ektp.ektpcreateaccount.viewmodel

import androidx.lifecycle.ViewModel
import com.elektra.ektp.ektpsharedpreferences.EKPTUserApplication.Companion.preferences
import com.elektra.ektp.ektpsharedpreferences.EKTPUserPreferences

class EKTPCreateAccountRegisterFormViewModel: ViewModel() {

    fun getSavedRegisterData(): ArrayList<String>
    {
        val userData = arrayListOf<String>()
        userData.add(preferences.getNameUser())
        userData.add(preferences.getPaternalUser())
        userData.add(preferences.getMaternalUser())
        userData.add(preferences.getBirthDateUser())
        userData.add(preferences.getBirthSiteUser())
        userData.add(preferences.getGenderUser())
        return userData
    }
}