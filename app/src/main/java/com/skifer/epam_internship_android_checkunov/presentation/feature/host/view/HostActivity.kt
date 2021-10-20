package com.skifer.epam_internship_android_checkunov.presentation.feature.host.view

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.skifer.epam_internship_android_checkunov.R

/**
 * Main Activity for the whole application
 */
class HostActivity: AppCompatActivity(){

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host)
        navController = (supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment).navController
    }
}

fun Activity.showStatusBar(isShown: Boolean) {
    window.apply {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            ViewCompat.getWindowInsetsController(this.decorView)?.apply {
                isAppearanceLightStatusBars = isShown
                setTranslucent(!isShown)
            }
        } else {
            decorView.systemUiVisibility =
                if (isShown) {
                    decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                } else {
                    decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
                }
            if (isShown)
                clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            else
                setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                )
        }
    }
}
