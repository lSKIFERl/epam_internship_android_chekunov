package com.skifer.epam_internship_android_checkunov.di.modules

import android.content.Context
import androidx.room.Room
import com.skifer.epam_internship_android_checkunov.data.database.database.MealsModelsDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Provides
    @Singleton
    fun provideDataBase(
        context: Context
    ): MealsModelsDataBase =
        Room.databaseBuilder(
            context,
            MealsModelsDataBase::class.java,
            MealsModelsDataBase.NAME)
            .fallbackToDestructiveMigration()
            .build()
}