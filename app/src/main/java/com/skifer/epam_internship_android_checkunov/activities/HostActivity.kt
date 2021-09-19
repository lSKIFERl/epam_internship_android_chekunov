package com.skifer.epam_internship_android_checkunov.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.skifer.epam_internship_android_checkunov.R
import com.skifer.epam_internship_android_checkunov.fragments.HostFragment
import com.skifer.epam_internship_android_checkunov.fragments.MealListFragment

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