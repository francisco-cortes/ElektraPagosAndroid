package com.elektra.ektp.ektputilies.uservalidations

class UserValidations {
    fun checkValidInput(wordToCheck: String): Boolean{
        return wordToCheck.matches("^[a-zA-ZÀ-ÿ\\u00f1\\u00d1.]+(\\s*[a-zA-ZÀ-ÿ\\u00f1\\u00d1.])*[a-zA-ZÀ-ÿ\\u00f1\\u00d1.]+(\\s|$)".toRegex())
                && wordToCheck != ""
    }

    fun checkValidDate(dateToCheck: String): Boolean{
        return dateToCheck.matches("^(0?[1-9]|[1-2][0-9]|3[0-1])(/)(0?[1-9]|1[012])(/)(\\d{4})".toRegex())
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
        return codeChar1 + codeChar2 + codeChar3 + codeChar4 + codeChar5
    }

    fun codeLenghtChecker(codeString: String): Boolean {
        return codeString.length == 5
    }

    fun checkZipCode(zipCodeUser: String): Boolean {
        return zipCodeUser.matches("^(?!00)([0-9]{2})([\\d]{3})".toRegex())
    }

    fun checkAddress(wordToCheck: String): Boolean {
        return wordToCheck.matches("^[A-Za-z0-9Á-ý.\\- ]*[A-Za-z0-9Á-ý.\\-][A-Za-z0-9Á-ý.\\- ]*$".toRegex())
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
        return checkZipCode(zipCodeUser) && checkAddress(colonyUser) && checkAddress(streetUser)
                && checkAddress(exteriorUser)
                && countryUser != "Selecciona una opción*" && stateUser != "Selecciona una opción*" && townUser != "Selecciona una opción*"
                && completed == 7
    }

    fun checkRepeatedChars(passString: String): Boolean{
        return !passString.matches(".*(\\w)\\1.*".toRegex())
    }

    fun checkBankString(passString: String): Boolean{
        return !passString.matches(".*\\w*((?i)BancoAzteca|Banco|Azteca|elektra(?-i))\\w*".toRegex())
    }

    fun checkConsecutiveString(passString: String): Boolean{
        return !passString.matches(".*\\w*((?i)abc|bcd|cde|def|efg|fgh|ghi|hij|ijk|jkl|klm|lmn|mno|nop|opq|pqr|qrs|rst|stu|tuv|uvw|vwx|wxy|xyz|012|123|234|345|456|567|678|789(?-i))\\w*".toRegex())
    }

}