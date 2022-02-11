package com.elektra.ektp.ektpforgottenpass.view.viewmodel

import androidx.lifecycle.ViewModel

class EKTPForgottenPassAuthorizationCodeViewModel: ViewModel() {
    //Fun to check the authentication code
    fun checkCodeAuthentication(codeAuth: String): Boolean{
        return codeAuth != "69420"
    }
}