package com.elektra.ektp.ektprepository.model


import com.squareup.moshi.Json

data class EKTPVerificarCodigoSMSTwiloRequest(
    @Json(name = "codigoEnviado")
    val codigoEnviado: String,
    @Json(name = "celularUsuario")
    val celularUsuario: String,
    @Json(name = "idServicioNotificacion")
    val idServicioNotificacion: String
)