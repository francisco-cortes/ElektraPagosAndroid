package com.elektra.ektp.ektpcreateaccount.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elektra.ektp.ektprepository.model.EKTPAltaUpgradeRequest
import com.elektra.ektp.ektprepository.model.EKTPConsultaFolioClienteRequest
import com.elektra.ektp.ektprepository.model.EKTPConsultaFolioClienteResponse
import com.elektra.ektp.ektprepository.model.EKTPFolioValidacionClientesResponse
import com.elektra.ektp.ektprepository.network.EKTPFolioValidacionClientesApi
import com.elektra.ektp.ektpsharedpreferences.EKTPUserApplication.Companion.preferences
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import java.lang.Exception
import java.lang.NullPointerException
import java.net.HttpRetryException

class EKTPCreateAccountRegisterFormViewModel: ViewModel() {

    var canContinue = false
    fun getSavedRegisterData(): ArrayList<String>
    {
        val userData = arrayListOf<String>()
        userData.add(preferences.getNameUser())
        userData.add(preferences.getPaternalUser())
        userData.add(preferences.getMaternalUser())
        userData.add(preferences.getBirthDateUser())
        userData.add(preferences.getBirthSiteUser())
        userData.add(preferences.getGenderUser())
        return userData
    }
    fun apiAltaUpdate(folio:String,str:String,numInt:String,numExt:String,col:String,cp:String,pob:String,state:String, nac: String, phone: String, idComp: String, folioId: String, mail: String, civilStat: String, act: String, idType:Int, birthCountry: String) : Job {
        val jobValue =  viewModelScope.launch {
            val response = try {
                EKTPFolioValidacionClientesApi.folioValidacionClientesTRetrofitService.putAltaUpgrade(
                    EKTPAltaUpgradeRequest(folio,str,numExt,numInt,col,cp,pob,state,nac,phone,idComp,folioId,mail,civilStat,act,idType,birthCountry))
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
            val body4 = response.headers()!!
            if (body3 in 200..299){
                canContinue = true
            }
            Log.v("APITEST","${body3}")
            Log.v("APITEST","${response.body()}")
            Log.v("APITEST","${body4}")
        }
        return jobValue
    }

    fun apiConsultaFolioCliente() : Job {
        val jobValue =  viewModelScope.launch {
            val response: Response<EKTPConsultaFolioClienteResponse> = try {
                EKTPFolioValidacionClientesApi.folioValidacionClientesTRetrofitService
                    .postConsultaFolioCliente(preferences.getEmailUser(), preferences.getPhoneUser())
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
            //val body3 = response.raw().request().url()
            try {
                val body3 : EKTPConsultaFolioClienteResponse = response.body()!!
                if (body3.mensaje=="Cliente encontrado."){
                    preferences.saveFolioCliente(body3.folio)
                }
                Log.v("APITEST","${body3}")
            }catch (e: NullPointerException){
                Log.v("APITEST","null")
            }
        }
        return jobValue
    }
}