package com.elektra.ektp.ektpcreateaccount.viewmodel

import android.R
import android.content.Context
import androidx.lifecycle.ViewModel

class EKTPCreateAccountViewModel(): ViewModel() {

    fun checkValidInput(wordToCheck: String): Boolean{
        return wordToCheck.matches("^[a-zA-ZÀ-ÿ\\u00f1\\u00d1.]+(\\s*[a-zA-ZÀ-ÿ\\u00f1\\u00d1.])*[a-zA-ZÀ-ÿ\\u00f1\\u00d1.]+(\\s|$)".toRegex())
                && wordToCheck != ""
    }

    fun checkValidDate(dateToCheck: String): Boolean{
        return dateToCheck.matches("^(0?[1-9]|[1-2][0-9]|3[0-1])(\\/)(0?[1-9]|1[012])(\\/)(19[0-9]{2}|200[0-4])".toRegex())
                && dateToCheck.isNotEmpty()
    }

    fun checkEmail(emailUser: String): Boolean{
        return android.util.Patterns.EMAIL_ADDRESS.matcher(emailUser).matches()
                && emailUser.isNotEmpty()
    }

    fun checkPhoneNumber(phoneUser: String): Boolean{
        return android.util.Patterns.PHONE.matcher(phoneUser).matches() && phoneUser.length == 10
    }

    fun checkFilledFields(
        nameUser: String, paternalLast: String, birthDate: String, birthState: String,
        phone: String, eMailText: String, emailConfirmationText: String, gender: String
    ): Boolean{
        return checkValidInput(nameUser) && checkValidInput(paternalLast) && checkValidDate(birthDate)
                && birthState != "Selecciona una opción*" && checkPhoneNumber(phone)
                && checkEmail(eMailText) && emailConfirmationText == eMailText && gender.isNotEmpty()
    }

}