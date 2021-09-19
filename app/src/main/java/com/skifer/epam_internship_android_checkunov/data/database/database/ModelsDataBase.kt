package com.skifer.epam_internship_android_checkunov.data.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.skifer.epam_internship_android_checkunov.data.database.dao.TypeModelDao
import com.skifer.epam_internship_android_checkunov.data.database.entities.TypeModelDB

@Database(entities = [TypeModelDB::class], version = 1)
abstract class ModelsDataBase: RoomDatabase() {
    abstract fun getTypeModelDao(): TypeModelDao

    companion object {
        val NAME = "MODELS_DATA_BASE"
    }
}