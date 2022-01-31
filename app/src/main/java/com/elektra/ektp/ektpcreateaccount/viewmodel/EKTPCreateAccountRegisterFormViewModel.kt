package com.elektra.ektp.ektpcreateaccount.viewmodel

import androidx.lifecycle.ViewModel
import com.elektra.ektp.ektpsharedpreferences.EKPTUserApplication.Companion.preferences
import com.elektra.ektp.ektpsharedpreferences.EKTPUserPreferences

class EKTPCreateAccountRegisterFormViewModel: ViewModel() {

    fun getSavedRegisterData(): Array<String>
    {
        val userData: Array<String> = emptyArray<String>()
        userData[0] = preferences.getNameUser()
        userData[1] = preferences.getPaternalUser()
        userData[2] = preferences.getMaternalUser()
        userData[3] = preferences.getBirthDateUser()
        userData[4] = preferences.getBirthSiteUser()
        userData[5] = preferences.getGenderUser()
        return userData
    }
}