package com.elektra.ektp.ektpsharedpreferences

import android.app.Application

class EKTPUserApplication: Application() {
    companion object{
        lateinit var preferences: EKTPUserPreferences
    }

    override fun onCreate() {
        super.onCreate()
        preferences = EKTPUserPreferences(applicationContext)
    }
}