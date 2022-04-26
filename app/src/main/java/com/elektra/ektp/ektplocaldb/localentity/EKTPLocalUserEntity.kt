package com.elektra.ektp.ektplocaldb.localentity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "LocalUser")
data class EKTPLocalUserEntity(
    @PrimaryKey(autoGenerate = true)
    @NonNull @ColumnInfo(name = "id") val id: Int = 0,
    @NonNull @ColumnInfo(name = "userName") val userName: String,
    @NonNull @ColumnInfo(name = "userPaternalName") val userPaternalName: String,
    @ColumnInfo(name = "userMaternalName") val userMaternalName: String,
    @NonNull @ColumnInfo(name = "userBirthDate") val userBirthDate: String,
    @NonNull @ColumnInfo(name = "userBirthPlace") val userBirthPlace: String,
    @NonNull @ColumnInfo(name = "userGenre") val userGenre: String,
    @NonNull @ColumnInfo(name = "userTelNum") val userTelNum: String,
    @NonNull @ColumnInfo(name = "userEmail") val userEmail: String,
    @NonNull @ColumnInfo(name = "userPC") val userPC: String,
    @NonNull @ColumnInfo(name = "userSettlement") val userSettlement: String,
    @NonNull @ColumnInfo(name = "userStreet") val userStreet: String,
    @NonNull @ColumnInfo(name = "userExtNum") val userExtNum: String,
    @ColumnInfo(name = "userIntNum") val userIntNum: String,
    @NonNull @ColumnInfo(name = "userCountry") val userCountry: String,
    @NonNull @ColumnInfo(name = "userState") val userState: String,
    @NonNull @ColumnInfo(name = "userTown") val userTown: String
)