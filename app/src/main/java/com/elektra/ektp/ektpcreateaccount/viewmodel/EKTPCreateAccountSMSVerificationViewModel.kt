package com.elektra.ektp.ektpcreateaccount.viewmodel


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elektra.ektp.ektprepository.model.EKTPCodigoSMSTwiloRequest
import com.elektra.ektp.ektprepository.model.EKTPVerificarCodigoSMSTwiloRequest
import com.elektra.ektp.ektprepository.model.EKTPVerificarCodigoSMSTwiloResponse
import com.elektra.ektp.ektprepository.network.EKTPFolioValidacionClientesApi
import com.elektra.ektp.ektpsharedpreferences.EKTPUserApplication.Companion.preferences
import kotlinx.coroutines.*
import retrofit2.Response
import java.io.IOException
import java.lang.Exception
import java.lang.NullPointerException
import java.net.HttpRetryException

class EKTPCreateAccountSMSVerificationViewModel: ViewModel() {

    var canContinue = false

    fun resquestSMSTwiloCode() {
        viewModelScope.launch {
            val response = try {
                EKTPFolioValidacionClientesApi.folioValidacionClientesTRetrofitService.getSMS(
                    EKTPCodigoSMSTwiloRequest("sms","+521${preferences.getPhoneUser()}",""))
                //EKTPFolioValidacionClientesApi.folioValidacionClientesTRetrofitServ1ice.getStatus()
                //RequestCodeApi.requestSMSRetrofitService.getCode(TestDataClass("eve.holt@reqres.in","pistol"))
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
            val body3 = response.code()
            Log.v("APITEST","$body3")
        }
    }

    fun verifySMSTwiloCode(smsCode: String) : Job {
        val value = viewModelScope.launch {
            val response : Response<EKTPVerificarCodigoSMSTwiloResponse> = try {
                EKTPFolioValidacionClientesApi.folioValidacionClientesTRetrofitService.verifySMS(
                    EKTPVerificarCodigoSMSTwiloRequest(smsCode,"+521${preferences.getPhoneUser()}",""))
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
            try {
                val body3 : EKTPVerificarCodigoSMSTwiloResponse = response.body()!!
                if (body3.mensaje== "Approved") {
                    canContinue = true
                    preferences.saveFolioTwilo(body3.folio)
                }
                Log.v("APITEST","$body3")
            }catch (e: NullPointerException){
                Log.v("APITEST","null")
            }
        }
        return value
    }
}