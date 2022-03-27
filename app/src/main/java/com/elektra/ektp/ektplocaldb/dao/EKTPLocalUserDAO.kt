package com.elektra.ektp.ektplocaldb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.elektra.ektp.ektplocaldb.localentity.EKTPLocalUserEntity

@Dao
public interface EKTPLocalUserDAO{

    @Query("SELECT * FROM LocalUser WHERE id = 0")
    suspend fun getAllLocalUserData():EKTPLocalUserEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllLocalUserData(localUserEntity: EKTPLocalUserEntity)
}