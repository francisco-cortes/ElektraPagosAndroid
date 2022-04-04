package com.elektra.ektp.ektprepository.model


import com.squareup.moshi.Json

data class EKTPCodigoSMSTwiloRequest(
    @Json(name = "canal")
    val canal: String,
    @Json(name = "celularUsuario")
    val celularUsuario: String,
    @Json(name = "idServicioNotificacion")
    val idServicioNotificacion: String
)