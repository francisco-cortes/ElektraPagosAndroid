package com.elektra.ektp.ektprepository.model


import com.squareup.moshi.Json

data class EKTPConsultaFolioClienteResponse(
    @Json(name = "codigo")
    val codigo: String,
    @Json(name = "mensaje")
    val mensaje: String,
    @Json(name = "folio")
    val folio: String,
    @Json(name = "resultado")
    val resultado: Any?
)