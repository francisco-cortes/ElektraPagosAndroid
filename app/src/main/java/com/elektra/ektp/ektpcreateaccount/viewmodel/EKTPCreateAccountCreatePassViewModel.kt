package com.elektra.ektp.ektpcreateaccount.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elektra.ektp.ektprepository.model.EKTPDatosExtraRequest
import com.elektra.ektp.ektprepository.network.EKTPFolioValidacionClientesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.Exception
import java.net.HttpRetryException

class EKTPCreateAccountCreatePassViewModel : ViewModel() {
    var canContinue = false

    fun apiDatosExtra(pc: String, col: String, stateB: String, pass: String, folio: String, stret: String, numExt: String, numInt: String, settle: String) : Job {
        val jobValue =  viewModelScope.launch {
            val response = try {
                EKTPFolioValidacionClientesApi.folioValidacionClientesTRetrofitService.putDatosExtra(
                    EKTPDatosExtraRequest(1,pc,col,"Mexíco","Mexicana",stateB,pass,folio,stret,numExt,numInt,settle,0,0,"","",""))
                //EKTPFolioValidacionClientesApi.folioValidacionClientesTRetrofitService.getStatus()
                //RequestCodeApi.requestSMSRetrofitService.getCode(TestDataClass("eve.holt@reqres.in","pistol"))
            } catch (e: IOException) {
                Log.e("MainActivity", "You might not have internet connection + $e")
                return@launch
            } catch (e: HttpRetryException){
                Log.e("MainActivity", "Unexpected response + $e")
                return@launch
            }
            catch (e: Exception){
                Log.e("MainActivity", "Unexpected response + $e")
                return@launch
            }
            val body3 = response.code()!!
            if (body3==200){
                canContinue = true
            }
            Log.v("APITEST","${body3}")
        }
        return jobValue
    }
}