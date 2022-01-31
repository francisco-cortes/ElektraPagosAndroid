package com.elektra.ektp.ektptoaster

import android.content.Context
import android.widget.Toast

class EKTPToaster {
    //requiere la activity como contexto y el mensaje
    fun makeAToast(context: Context, message: String){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}