package com.elektra.ektp.ektplocaldb.localentity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "LocalUser")
data class EKTPLocalUser(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "userName") val userName: String,
    @ColumnInfo(name = "userPaternalName") val userPaternalName: String,
    @ColumnInfo(name = "userMaternalName") val userMaternalName: String,
    @ColumnInfo(name = "userBirthDate") val userBirthDate: String,
    @ColumnInfo(name = "userBirthPlace") val userBirthPlace: String,
    @ColumnInfo(name = "userGenre") val userGenre: String,
    @ColumnInfo(name = "userTelNum") val userTelNum: String,
    @ColumnInfo(name = "userEmail") val userEmail: String,
    @ColumnInfo(name = "userPC") val userPC: String,
    @ColumnInfo(name = "userSettlement") val userSettlement: String,
    @ColumnInfo(name = "userStreet") val userStreet: String,
    @ColumnInfo(name = "userExtNum") val userExtNum: String,
    @ColumnInfo(name = "userIntNum") val userIntNum: String,
    @ColumnInfo(name = "userCountry") val userCountry: String,
    @ColumnInfo(name = "userState") val userState: String,
    @ColumnInfo(name = "userTown") val userTown: String
) {
}