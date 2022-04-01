package com.elektra.ektp.ektpcreateaccount.viewmodel

import android.content.Context
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.elektra.ektp.R
import com.elektra.ektp.ektpcreateaccount.viewmodel.EKTPCreateAccountActivityViewModel.Companion.folio
import com.elektra.ektp.ektprepository.model.EKTPCodigoSMSTwiloRequest
import com.elektra.ektp.ektprepository.model.EKTPFolioValidacionClientesResponse
import com.elektra.ektp.ektprepository.model.EKTPVerificarCodigoSMSTwiloRequest
import com.elektra.ektp.ektprepository.model.EKTPVerificarCodigoSMSTwiloResponse
import com.elektra.ektp.ektprepository.network.EKTPFolioValidacionClientesApi
import kotlinx.coroutines.*
import retrofit2.Response
import java.io.IOException
import java.lang.Exception
import java.net.HttpRetryException

class EKTPCreateAccountSMSVerificationViewModel: ViewModel() {

    var canContinueñero = false

    fun checkSMSVerification(smsCode: String): Boolean{
        return smsCode == "69420"
    }

    /*fun apiTest2() {
        viewModelScope.launch {
            val response : Response<List<swaggerGetTest>> = try {
                EKTPFolioValidacionClientesApi.folioValidacionClientesTRetrofitService.getStatus()
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
            val body3 : List<swaggerGetTest> = response.body()!!
            Log.v("APITEST","${body3}")
        }
    }*/

    fun resquestSMSTwiloCode(telNum: String) {
        viewModelScope.launch {
            val response = try {
                EKTPFolioValidacionClientesApi.folioValidacionClientesTRetrofitService.getSMS(
                    EKTPCodigoSMSTwiloRequest("sms","+521${telNum}",""))
                //EKTPFolioValidacionClientesApi.folioValidacionClientesTRetrofitServ1ice.getStatus()
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
            Log.v("APITEST","${body3}")
        }
    }

    fun verifySMSTwiloCode(smsCode: String,telNum: String) : Job {
        val value =  viewModelScope.launch {
            val response : Response<EKTPVerificarCodigoSMSTwiloResponse> = try {
                EKTPFolioValidacionClientesApi.folioValidacionClientesTRetrofitService.verifySMS(
                    EKTPVerificarCodigoSMSTwiloRequest("${smsCode}","+521${telNum}",""))
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
            val body3 : EKTPVerificarCodigoSMSTwiloResponse = response.body()!!
            if (body3.mensaje== "Approved") {
                canContinueñero = true
                folio =  body3.folio
                }
            Log.v("APITEST","${body3}")
        }
        return value
    }
}