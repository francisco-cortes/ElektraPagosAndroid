package com.elektra.ektp.ektplocaldb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.elektra.ektp.ektplocaldb.dao.EKTPLocalUserDAO
import com.elektra.ektp.ektplocaldb.localentity.EKTPLocalUser

@Database(entities = [EKTPLocalUser::class], version = 1)
abstract class EKTPLocalDataBase : RoomDatabase() {

    abstract fun localUserDAO(): EKTPLocalUserDAO
}