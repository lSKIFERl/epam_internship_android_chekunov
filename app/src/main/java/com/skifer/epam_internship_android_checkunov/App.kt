package com.skifer.epam_internship_android_checkunov

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.skifer.epam_internship_android_checkunov.data.database.database.ModelsDataBase
import com.skifer.epam_internship_android_checkunov.di.AppComponent
import com.skifer.epam_internship_android_checkunov.di.DaggerAppComponent


class App : Application() {

    lateinit var database: ModelsDataBase

    lateinit var sharedPreferences: SharedPreferences

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        sharedPreferences = getSharedPreferences("last_instances", Context.MODE_PRIVATE)
        database = Room.databaseBuilder(this, ModelsDataBase::class.java, "models_data_base")
            .build()
        appComponent = DaggerAppComponent.builder().bindContext(applicationContext).build()
    }

    companion object {
        lateinit var instance: App
    }
}

fun Context.getAppComponent() =
    when (this) {
        is App -> appComponent
        else -> (this.applicationContext as App).appComponent
    }