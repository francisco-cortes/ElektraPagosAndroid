package com.elektra.ektp.ektprepository.model


import com.squareup.moshi.Json

data class EKTPAltaUpgradeRequest(
    @Json(name = "folio_validacion")
    val folioValidacion: String,
    @Json(name = "calle")
    val calle: String,
    @Json(name = "numero_exterior")
    val numeroExterior: String,
    @Json(name = "numero_interior")
    val numeroInterior: String,
    @Json(name = "colonia")
    val colonia: String,
    @Json(name = "codigo_postal")
    val codigoPostal: String,
    @Json(name = "poblacion")
    val poblacion: String,
    @Json(name = "estado")
    val estado: String,
    @Json(name = "nacionalidad")
    val nacionalidad: String,
    @Json(name = "culular")
    val culular: String,
    @Json(name = "id_compania")
    val idCompania: String,
    @Json(name = "folio_identificacion")
    val folioIdentificacion: String,
    @Json(name = "correo_electronico")
    val correoElectronico: String,
    @Json(name = "estado_civil")
    val estadoCivil: String,
    @Json(name = "actividad")
    val actividad: String,
    @Json(name = "tipo_identificacion")
    val tipoIdentificacion: Int,
    @Json(name = "pais_nacimiento")
    val paisNacimiento: String
)