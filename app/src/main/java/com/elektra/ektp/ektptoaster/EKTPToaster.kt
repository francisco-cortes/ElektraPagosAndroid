package com.elektra.ektp.ektptoaster

import android.content.Context
import android.widget.Toast

class EKTPToaster {
    
    fun makeAToast(context: Context, message: String){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}