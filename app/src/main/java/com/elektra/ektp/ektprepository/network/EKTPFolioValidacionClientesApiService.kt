package com.elektra.ektp.ektprepository.network

import com.elektra.ektp.ektprepository.model.EKTPCodigoSMSTwiloRequest
import com.elektra.ektp.ektprepository.model.EKTPFolioValidacionClientesResponse
import com.elektra.ektp.ektprepository.model.EKTPVerificarCodigoSMSTwiloRequest
import com.elektra.ektp.ektprepository.model.EKTPVerificarCodigoSMSTwiloResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

private const val BASE_URL = "http://bf1a-189-203-174-194.ngrok.io"

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

    /*@Headers("accept: application/json")
    @GET("/status")
    suspend fun getStatus(): Response<List<swaggerGetTest>>*/

    @Headers("accept: application/json")
    @POST("/clientes/obtenerTwilioCodigo")
    suspend fun getSMS(@Body body: EKTPCodigoSMSTwiloRequest) : Response<ResponseBody>

    @Headers("accept: application/json")
    @POST("/clientes/verificarTwilioCodigo")
    suspend fun verifySMS(@Body body: EKTPVerificarCodigoSMSTwiloRequest) : Response<EKTPVerificarCodigoSMSTwiloResponse>
}

object EKTPFolioValidacionClientesApi {
    val folioValidacionClientesTRetrofitService: EKTPFolioValidacionClientesApiService by lazy {
        retrofit.create(EKTPFolioValidacionClientesApiService::class.java)
    }
}
