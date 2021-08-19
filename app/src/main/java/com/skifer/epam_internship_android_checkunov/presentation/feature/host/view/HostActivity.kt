package com.skifer.epam_internship_android_checkunov.presentation.feature.host.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.skifer.epam_internship_android_checkunov.R

/**
 * Main Activity for the whole application
 */
class HostActivity: AppCompatActivity(){

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host)
        navController = (supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment).navController
    }
}