package com.elektra.ektp.ektputilies.ektptoaster

import android.content.Context
import android.widget.Toast

class EKTPToaster {
    //requires context and message, cant use to show massages trough toast
    fun makeAToast(context: Context, message: String){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
    //---
}