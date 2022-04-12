package com.elektra.ektp.ektpsharedpreferences

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class EKTPUserPreferences(val context: Context) {
    val SHARED_PREFERENCES_DB = "EKPT_DB"
    val SHARED_NAME_USER = "name"
    val SHARED_PATERNAL_USER = "paternalLastName"
    val SHARED_MATERNAL_USER = "maternalLastName"
    val SHARED_BIRTHDATE_USER = "birthDate"
    val SHARED_BIRTHSTATE_USER = "birthState"
    val SHARED_GENDER_USER = "gender"
    val SHARED_PHONE_USER = "phoneNumber"
    val SHARED_EMAIL_USER = "email"
    val SHARED_FILE = "EKTPUserSec"
    val SHARED_BIO_TYPE = "BiometricUSed"
    val SHARED_BIO_STATUS = "BioStatus"
    val SHARED_LOGIN_FRAGMENT = "isLoginWithBiometric"
    val SHARED_TEMPORAL_PASSWORD = "qwertyuio"
    val SHARED_TEMPORAL_LOCKED = "passwordIsLocked?"
    val SHARED_BIO_LOGIN_ACTIVATED = "bioLoginActivated?"
    val SHARED_LOCAL_STAUS = "vacio"
    val SHARED_API_DATE = "yyyy-mm-dd"
    val SHARED_FOLIO_TWILO = "folio twilo"
    val SHARED_FOLIO_CLIENTE = "folio cliente"


    private val enSharedPreferences: SharedPreferences = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        EncryptedSharedPreferences.create(
            context,
            SHARED_FILE,
            MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build(),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        ) else {
        context.getSharedPreferences(SHARED_FILE, 0)
    }

    fun saveNameUser(name: String){
        enSharedPreferences.edit().putString(SHARED_NAME_USER, name).apply()
    }

    fun savePaternalUser(paternalLast: String){
        enSharedPreferences.edit().putString(SHARED_PATERNAL_USER, paternalLast).apply()
    }

    fun saveMaternalUser(maternalLast: String){
        enSharedPreferences.edit().putString(SHARED_MATERNAL_USER, maternalLast).apply()
    }

    fun saveBirthDateUser(birthDate: String){
        enSharedPreferences.edit().putString(SHARED_BIRTHDATE_USER, birthDate).apply()
    }

    fun saveBirthStateUser(birthState: String){
        enSharedPreferences.edit().putString(SHARED_BIRTHSTATE_USER, birthState).apply()
    }

    fun saveGenderUser(genderUser: String){
        enSharedPreferences.edit().putString(SHARED_GENDER_USER, genderUser).apply()
    }

    fun savePhoneUser(phoneUser: String){
        enSharedPreferences.edit().putString(SHARED_PHONE_USER, phoneUser).apply()
    }

    fun saveEmailUser(eMailUser: String){
        enSharedPreferences.edit().putString(SHARED_EMAIL_USER, eMailUser).apply()
    }

    fun getNameUser(): String{
        return enSharedPreferences.getString(SHARED_NAME_USER, "")!!
    }

    fun getBirthSiteUser(): String {
        return enSharedPreferences.getString(SHARED_BIRTHSTATE_USER, "")!!
    }

    fun getGenderUser(): String {
        return enSharedPreferences.getString(SHARED_GENDER_USER, "")!!
    }

    fun getPaternalUser(): String {
        return enSharedPreferences.getString(SHARED_PATERNAL_USER, "")!!
    }

    fun getMaternalUser(): String {
        return enSharedPreferences.getString(SHARED_MATERNAL_USER, "")!!
    }

    fun getBirthDateUser(): String {
        return enSharedPreferences.getString(SHARED_BIRTHDATE_USER, "")!!
    }

    fun saveBioType(bioType:Int){
        enSharedPreferences.edit().putInt(SHARED_BIO_TYPE,bioType).apply()
    }

    fun getBioType():Int{
        return enSharedPreferences.getInt(SHARED_BIO_TYPE,3)!!
    }

    fun saveBioStatus(bioStatus: Int){
        enSharedPreferences.edit().putInt(SHARED_BIO_STATUS,bioStatus).apply()
    }

    fun getBioStatus():Int{
        return enSharedPreferences.getInt(SHARED_BIO_STATUS,0)!!
    }

    fun getPhoneUser():String{
        return enSharedPreferences.getString(SHARED_PHONE_USER,"")!!
    }

    fun getEmailUser():String{
        return enSharedPreferences.getString(SHARED_EMAIL_USER,"")!!
    }

    fun saveLoginWithBio(isBioLogin: Boolean){
        enSharedPreferences.edit().putBoolean(SHARED_LOGIN_FRAGMENT,isBioLogin).apply()
    }

    fun getLoginWithBio(): Boolean{
        return  enSharedPreferences.getBoolean(SHARED_LOGIN_FRAGMENT,true)!!
    }

    fun saveTemporalPassword(temporalPassword: String){
        enSharedPreferences.edit().putString(SHARED_TEMPORAL_PASSWORD,temporalPassword).apply()
    }

    fun getTemporalPassword(): String{
        return enSharedPreferences.getString(SHARED_TEMPORAL_PASSWORD,"")!!
    }

    fun saveTemporalLocked(isLock: Boolean){
        enSharedPreferences.edit().putBoolean(SHARED_TEMPORAL_LOCKED,isLock).apply()
    }

    fun getTemporalLocked(): Boolean{
        return enSharedPreferences.getBoolean(SHARED_TEMPORAL_LOCKED,false)!!
    }

    fun saveBioLogin(bioLoginActivate: Boolean){
        enSharedPreferences.edit().putBoolean(SHARED_BIO_LOGIN_ACTIVATED,bioLoginActivate).apply()
    }

    fun getBioLogin():Boolean{
        return enSharedPreferences.getBoolean(SHARED_BIO_LOGIN_ACTIVATED,false)!!
    }
    fun saveLocalStatus(localStatus:String){
        enSharedPreferences.edit().putString(SHARED_LOCAL_STAUS,localStatus).apply()
    }
    fun getLocalStatus():String{
        return enSharedPreferences.getString(SHARED_LOCAL_STAUS,"")!!
    }
    fun saveApiDate(apiDate:String){
        enSharedPreferences.edit().putString(SHARED_API_DATE,apiDate).apply()
    }
    fun getApiDate():String{
        return enSharedPreferences.getString(SHARED_API_DATE,"")!!
    }
    fun saveFolioTwilo(folio:String){
        enSharedPreferences.edit().putString(SHARED_FOLIO_TWILO,folio).apply()
    }
    fun getFolioTwilo():String{
        return enSharedPreferences.getString(SHARED_FOLIO_TWILO,"")!!
    }
    fun saveFolioCliente(folio:String){
        enSharedPreferences.edit().putString(SHARED_FOLIO_CLIENTE,folio).apply()
    }
    fun getFolioCliente():String{
        return enSharedPreferences.getString(SHARED_FOLIO_CLIENTE,"")!!
    }
}