package com.skifer.epam_internship_android_checkunov.presentation.feature.host.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.skifer.epam_internship_android_checkunov.R
import com.skifer.epam_internship_android_checkunov.presentation.feature.meals.view.MealListFragment

/**
 * Main Activity for the whole application
 */
class HostActivity: AppCompatActivity(), FragmentsCommunicate {

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

    override fun update() {
        (supportFragmentManager.findFragmentById(R.id.meal_list_container) as MealListFragment).update()
    }
}

interface FragmentsCommunicate {
    fun update()
}