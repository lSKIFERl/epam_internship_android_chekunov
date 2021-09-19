package com.skifer.epam_internship_android_checkunov.di.modules

import android.content.Context
import androidx.room.Room
import com.skifer.epam_internship_android_checkunov.data.database.database.ModelsDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Provides
    @Singleton
    fun provideDataBase(
        context: Context
    ): ModelsDataBase =
        Room.databaseBuilder(
            context,
            ModelsDataBase::class.java,
            ModelsDataBase.NAME)
            .build()
}