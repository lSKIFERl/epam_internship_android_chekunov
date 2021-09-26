package com.skifer.epam_internship_android_checkunov.data.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.skifer.epam_internship_android_checkunov.data.database.dao.CategoryModelDao
import com.skifer.epam_internship_android_checkunov.data.database.entities.CategoryModelDB

@Database(
    entities = [CategoryModelDB::class],
    version = 2,
)
abstract class MealsModelsDataBase: RoomDatabase() {

    abstract fun getTypeModelDao(): CategoryModelDao

    companion object {
        val NAME = "MODELS_DATA_BASE"
    }
}