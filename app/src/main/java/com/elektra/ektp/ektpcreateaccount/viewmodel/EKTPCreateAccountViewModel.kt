package com.elektra.ektp.ektpcreateaccount.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elektra.ektp.ektprepository.model.EKTPFolioValidacionClientesResponse
import com.elektra.ektp.ektprepository.network.EKTPFolioValidacionClientesApi
import com.elektra.ektp.ektpsharedpreferences.EKTPUserApplication.Companion.preferences
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.Exception
import java.net.HttpRetryException

class EKTPCreateAccountViewModel: ViewModel() {

    var canContinue = false

    fun saveRegisterData(nameUser: String, paternalLast: String, maternalLast: String, birthDate: String, birthState: String,
        phone: String, eMailText: String, gender: String, apiDate:String){
        preferences.saveNameUser(nameUser)
        preferences.savePaternalUser(paternalLast)
        preferences.saveMaternalUser(maternalLast)
        preferences.saveBirthDateUser(birthDate)
        preferences.saveBirthStateUser(birthState)
        preferences.saveGenderUser(gender)
        preferences.saveEmailUser(eMailText)
        preferences.savePhoneUser(phone)
        preferences.saveApiDate(apiDate)
    }

    fun apiFolioValClientes(phone: String, name: String, pName: String, mName: String, bDay: String, genre: String, mail: String, stateBirth: String, folio: String) : Job {
        val jobValue =  viewModelScope.launch {
            val response = try {
                EKTPFolioValidacionClientesApi.folioValidacionClientesTRetrofitService.postValidation(
                    EKTPFolioValidacionClientesResponse(phone, name, pName,mName,bDay,genre,mail,stateBirth,folio))
            } catch (e: IOException) {
                Log.e("APITEST", "You might not have internet connection + $e")
                return@launch
            } catch (e: HttpRetryException){
                Log.e("APITEST", "Unexpected response + $e")
                return@launch
            }
            catch (e: Exception){
                Log.e("APITEST", "Unexpected response + $e")
                return@launch
            }
            val body3 = response.code()!!
            if (body3 in 200..299){
                canContinue = true
            }
            Log.v("APITEST","${body3}")
        }
        return jobValue
    }
}