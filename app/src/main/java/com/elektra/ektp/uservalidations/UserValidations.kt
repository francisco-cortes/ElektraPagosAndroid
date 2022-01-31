package com.elektra.ektp.uservalidations

class UserValidations {
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

    fun concatenaterCode(codeChar1: String, codeChar2: String, codeChar3: String, codeChar4: String, codeChar5: String): String {
        var codeString = ""
        codeString = codeChar1 + codeChar2 + codeChar3 + codeChar4 + codeChar5
        return codeString
    }

    fun codeLenghtChecker(codeString: String): Boolean {
        return codeString.length == 5
    }

    fun checkZipCode(zipCodeUser: String): Boolean {
        return zipCodeUser.matches("^(?!00)([0-9]{2})([\\d]{3})".toRegex())
    }

    fun checkAddress(wordToCheck: String): Boolean {
        return wordToCheck.matches("^[A-Za-z0-9Á-ý. ]*[A-Za-z0-9Á-ý.][A-Za-z0-9Á-ý. ]*$".toRegex())
    }

    fun checkFieldsProgressBar(
        zipCodeUser: String,
        colonyUser: String,
        streetUser: String,
        exteriorUser: String,
        countryUser: String,
        stateUser: String,
        townUser: String,
        completed: Int
    ): Boolean {
        return checkZipCode(zipCodeUser) && checkValidInput(colonyUser) && checkValidInput(streetUser)
                && checkValidInput(exteriorUser)
                && countryUser != "Selecciona una opción*" && stateUser != "Selecciona una opción*" && townUser != "Selecciona una opción*"
                && completed == 7
    }
}