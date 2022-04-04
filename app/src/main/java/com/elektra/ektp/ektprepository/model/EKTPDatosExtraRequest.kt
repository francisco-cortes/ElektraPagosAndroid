package com.elektra.ektp.ektprepository.model


import com.squareup.moshi.Json

data class EKTPDatosExtraRequest(
    @Json(name = "id")
    val id: Int,
    @Json(name = "postalCode")
    val postalCode: String,
    @Json(name = "colony")
    val colony: String,
    @Json(name = "countryOfBirth")
    val countryOfBirth: String,
    @Json(name = "nacionality")
    val nacionality: String,
    @Json(name = "state")
    val state: String,
    @Json(name = "password")
    val password: String,
    @Json(name = "folio")
    val folio: String,
    @Json(name = "street")
    val street: String,
    @Json(name = "stNumberExt")
    val stNumberExt: String,
    @Json(name = "stNumberInt")
    val stNumberInt: String,
    @Json(name = "population")
    val population: String,
    @Json(name = "idCompany")
    val idCompany: Int,
    @Json(name = "typeId")
    val typeId: Int,
    @Json(name = "folioId")
    val folioId: String,
    @Json(name = "civilStatus")
    val civilStatus: String,
    @Json(name = "activity")
    val activity: String
)