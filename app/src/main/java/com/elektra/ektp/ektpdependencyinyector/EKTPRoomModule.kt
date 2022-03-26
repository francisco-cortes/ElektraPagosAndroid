package com.elektra.ektp.ektpdependencyinyector

import android.content.Context
import androidx.room.Room
import com.elektra.ektp.ektplocaldb.EKTPLocalDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EKTPRoomModule {

    private const val DATA_BASE_NAME = "ElektraPagosDB"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
    Room.databaseBuilder(context, EKTPLocalDataBase::class.java, DATA_BASE_NAME).build()

    @Singleton
    @Provides
    fun provideLocalUserDao(db: EKTPLocalDataBase) = db.localUserDAO()
}