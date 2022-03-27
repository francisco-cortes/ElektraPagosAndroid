package com.elektra.ektp.ektplocaldb

import android.app.Application
import android.content.Context
import androidx.room.Room

class LocalDB(context: Context) : Application() {
    val room =
        Room.databaseBuilder(context, EKTPLocalDataBase::class.java,"ElektraPagosDB")
            .build()
}