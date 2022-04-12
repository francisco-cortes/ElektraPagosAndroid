package com.elektra.ektp.ektprepository.model

import com.squareup.moshi.Json

data class EKTPConsultaFolioClienteRequest(
    @Json(name = "correo") val correo:String,
    @Json(name = "telefono") val telefono:String) {
}