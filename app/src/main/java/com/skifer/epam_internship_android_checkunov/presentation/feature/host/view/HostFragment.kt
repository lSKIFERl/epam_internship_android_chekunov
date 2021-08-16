package com.skifer.epam_internship_android_checkunov.presentation.feature.host.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.skifer.epam_internship_android_checkunov.R
import com.skifer.epam_internship_android_checkunov.presentation.feature.settings.view.SettingsFragment
import com.skifer.epam_internship_android_checkunov.presentation.feature.types.view.TypeListFragment


/**
 * First and main App Fragment
 */
class HostFragment : Fragment(R.layout.fragment_host) {

    /**
     * Starts [MealListFragment] immediately
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("Net", "Loading types List")
        val l = TypeListFragment.newInstance()
        parentFragmentManager
            .beginTransaction()
            .replace(
                    R.id.type_list_container,
                    TypeListFragment.newInstance()
                )
            .addToBackStack(TypeListFragment.TAG)
            .commit()
        val actionBarToolBar: Toolbar = view.findViewById(R.id.toolbar_home) as Toolbar
        actionBarToolBar.inflateMenu(R.menu.menu_host)
        actionBarToolBar.setOnMenuItemClickListener {
            val bottomSheet = SettingsFragment.newInstance()
            bottomSheet.show(parentFragmentManager, bottomSheet.tag)
            return@setOnMenuItemClickListener true
        }
    }

    companion object {
        private const val HOST_FRAGMENT = "HOST_FRAGMENT"

        /**
         * Should be called instead instead of just instantiating the class
         */
        fun newInstance() = HostFragment().apply {
            //arguments = bundleOf(HOST_FRAGMENT to ...) then get arguments somewhere
        }
    }

}