package com.skifer.epam_internship_android_checkunov

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.skifer.epam_internship_android_checkunov.data.database.database.ModelsDataBase


class App : Application() {

    lateinit var database: ModelsDataBase

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate() {
        super.onCreate()
        instance = this
        sharedPreferences = getSharedPreferences("last_instances", Context.MODE_PRIVATE)
        database = Room.databaseBuilder(this, ModelsDataBase::class.java, "models_data_base")
            .build()
    }

    companion object {
        lateinit var instance: App
    }
}