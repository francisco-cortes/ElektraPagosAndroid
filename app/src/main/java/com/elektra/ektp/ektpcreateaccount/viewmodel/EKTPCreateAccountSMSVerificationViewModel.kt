package com.elektra.ektp.ektpcreateaccount.viewmodel

import androidx.lifecycle.ViewModel

class EKTPCreateAccountSMSVerificationViewModel: ViewModel() {

    fun concatenaterCode(codeChar1: String, codeChar2: String, codeChar3: String, codeChar4: String, codeChar5: String): String {
        var codeString = ""
        codeString = codeChar1 + codeChar2 + codeChar3 + codeChar4 + codeChar5
        return codeString
    }

    fun codeLenghtChecker(codeString: String): Boolean {
        return codeString.length == 5
    }
}