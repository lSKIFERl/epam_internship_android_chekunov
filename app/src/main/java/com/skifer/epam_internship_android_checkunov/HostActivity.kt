package com.skifer.epam_internship_android_checkunov

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Main Activity for the whole application
 */
class HostActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host)

        supportFragmentManager
                .beginTransaction()
                .replace(
                        R.id.containerHost,
                        HostFragment.
                        newInstance()
                )
                .commit()
    }
}