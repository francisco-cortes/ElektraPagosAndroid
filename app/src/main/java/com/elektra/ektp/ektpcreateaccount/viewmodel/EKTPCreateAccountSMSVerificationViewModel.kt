package com.elektra.ektp.ektpcreateaccount.viewmodel

import androidx.lifecycle.ViewModel

class EKTPCreateAccountSMSVerificationViewModel: ViewModel() {

    fun checkSMSVerification(smsCode: String): Boolean{
        return smsCode != "69420"
    }
}