package com.skifer.epam_internship_android_checkunov

import android.app.Application
import android.content.Context
import com.skifer.epam_internship_android_checkunov.di.AppComponent
import com.skifer.epam_internship_android_checkunov.di.DaggerAppComponent


class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
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