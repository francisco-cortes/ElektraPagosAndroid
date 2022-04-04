package com.elektra.ektp.ektprepository.model


import com.squareup.moshi.Json

data class EKTPFolioValidacionClientesResponse(
    @Json(name = "phone")
    val phone: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "parental")
    val parental: String,
    @Json(name = "maternal")
    val maternal: String,
    @Json(name = "birthday")
    val birthday: String,
    @Json(name = "genre")
    val genre: String,
    @Json(name = "email")
    val email: String,
    @Json(name = "estateOfBirth")
    val estateOfBirth: String,
    @Json(name = "folio")
    val folio: String
)