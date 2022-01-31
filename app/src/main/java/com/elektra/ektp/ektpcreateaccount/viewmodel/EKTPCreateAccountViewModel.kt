package com.elektra.ektp.ektpcreateaccount.viewmodel

import android.R
import android.content.Context
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModel
import java.util.*

class EKTPCreateAccountViewModel(private val applicationContext: Context): ViewModel() {

    private var name: String = ""
    private var paternalLast: String = ""
    private var maternalLast: String = ""
    private var birthDate: String = ""
    private var birthState: String = ""
    private var gender: String = ""
    private var phone: String = "Selecciona una opción*"
    private var eMailText: String = ""
    private var emailConfirmationText: String = ""
    val c = Calendar.getInstance()
    val year = c.get(Calendar.YEAR)
    val month = c.get(Calendar.MONTH)
    val day = c.get(Calendar.DAY_OF_MONTH)

    fun checkValidInput(wordToCheck: String): Boolean{
        name = wordToCheck
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

    fun checkFilledFields(): Boolean{
        return checkValidInput(name) && checkValidInput(paternalLast) && checkValidDate(birthDate)
                && birthState != "Selecciona una opción*" && checkPhoneNumber(phone)
                && checkEmail(eMailText) && emailConfirmationText == eMailText && gender.isNotEmpty()
    }

}