package com.skifer.epam_internship_android_checkunov

import android.app.Application
import androidx.room.Room
import com.skifer.epam_internship_android_checkunov.data.database.database.ModelsDataBase


class App : Application() {

    private lateinit var database: ModelsDataBase

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(this, ModelsDataBase::class.java, "models_data_base")
            .build()
    }

    fun getDatabase(): ModelsDataBase {
        return database
    }

    companion object {
        var instance: App? = null
    }
}