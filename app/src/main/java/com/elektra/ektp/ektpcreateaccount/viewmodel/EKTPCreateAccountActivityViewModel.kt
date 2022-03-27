package com.elektra.ektp.ektpcreateaccount.viewmodel

import androidx.lifecycle.ViewModel
import com.elektra.ektp.ektplocaldb.LocalDB
import com.elektra.ektp.ektplocaldb.dao.EKTPLocalUserDAO
import com.elektra.ektp.ektplocaldb.localentity.EKTPLocalUserEntity
import dagger.hilt.android.qualifiers.ApplicationContext

class EKTPCreateAccountActivityViewModel(): ViewModel()  {
    companion object{
        var uName = ""
        var pName = ""
        var mName = ""
        var bDate = ""
        var bPlace = ""
        var uGenre = ""
        var uTel = ""
        var uMail = ""
        var uPC= ""
        var uSett = ""
        var uStr = ""
        var uNext = ""
        var uNint = ""
        var uCoun = ""
        var uStat = ""
        var uTown = ""
    }

    fun saveRegisterOnDB():EKTPLocalUserEntity{
        return EKTPLocalUserEntity(
            userName = uName,
        userPaternalName = pName,
        userMaternalName = mName,
        userBirthDate = bDate,
        userBirthPlace = bPlace,
        userGenre = uGenre,
        userTelNum = uTel,
        userEmail = uMail,
        userPC = uPC,
        userSettlement = uSett,
        userStreet = uStr,
        userExtNum = uNext,
        userIntNum = uNint,
        userCountry = uCoun,
        userState = uStat,
        userTown = uTown)
    }
}