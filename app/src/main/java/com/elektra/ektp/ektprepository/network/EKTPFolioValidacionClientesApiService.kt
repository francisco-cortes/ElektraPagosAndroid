package com.elektra.ektp.ektprepository.network

import com.elektra.ektp.ektprepository.model.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

private const val BASE_URL = "http://99c4-189-203-89-18.ngrok.io"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface EKTPFolioValidacionClientesApiService {
    @Headers("accept: application/json")
    @POST("/clientes/folioValidacionCliente")
    suspend fun postValidation(@Body body : EKTPFolioValidacionClientesResponse): Response<ResponseBody>

    @Headers("accept: application/json")
    @POST("/clientes/obtenerTwilioCodigo")
    suspend fun getSMS(@Body body: EKTPCodigoSMSTwiloRequest) : Response<ResponseBody>

    @Headers("accept: application/json")
    @POST("/clientes/verificarTwilioCodigo")
    suspend fun verifySMS(@Body body: EKTPVerificarCodigoSMSTwiloRequest) : Response<EKTPVerificarCodigoSMSTwiloResponse>

    @Headers("accept: application/json")
    @POST("/clientes/alta-upgrade")
    suspend fun putAltaUpgrade(@Body body: EKTPAltaUpgradeRequest ): Response<ResponseBody>

    @POST("/clientes/consultaFolioCliente")
    suspend fun postConsultaFolioCliente(@Query("correo") mail: String, @Query("telefono") tel: String ): Response<EKTPConsultaFolioClienteResponse>
}

object EKTPFolioValidacionClientesApi {
    val folioValidacionClientesTRetrofitService: EKTPFolioValidacionClientesApiService by lazy {
        retrofit.create(EKTPFolioValidacionClientesApiService::class.java)
    }
}
