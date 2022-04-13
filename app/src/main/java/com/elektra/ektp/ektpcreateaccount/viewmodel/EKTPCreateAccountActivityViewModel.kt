package com.elektra.ektp.ektpcreateaccount.viewmodel

import androidx.lifecycle.ViewModel
import com.elektra.ektp.ektplocaldb.localentity.EKTPLocalUserEntity

class EKTPCreateAccountActivityViewModel: ViewModel()  {
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