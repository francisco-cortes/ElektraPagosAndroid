package com.elektra.ektp.ektpmovements.model

//Data class for model
data class EKTPMovementsModel(
    var detailAmount: String = "",              //Monto del movimiento
    var detailTitle: String = "",               //Tipo de movimiento
    var detailDate: String = "",                //Fecha del movimiento
    var detailAccount: String = "",             //Cuenta destino
    var detailReceivedName: String = "",        //Nombre del emisor
    var detailConcept: String = "",             //Concepto
    var detailFolio: String = "",               //Folio
    var detailStatus: String = "",              //Estatus
    var detailMTCN: String = "",                //MTCN
    var detailConceptType: Boolean = false,     //Tipo de movimiento (retiro - false, depósito - true)
    var detailOperationType: String = "",       //Tipo de operación
    var detailWithdrewName: String = ""         //Nombre de quien retira
)
//---
