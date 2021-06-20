package com.skifer.epam_internship_android_checkunov

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View

class HostFragment : Fragment(R.layout.fragment_host) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.containerHost, MealListFragment())
            .commit()
    }

}