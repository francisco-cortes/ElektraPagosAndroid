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

}