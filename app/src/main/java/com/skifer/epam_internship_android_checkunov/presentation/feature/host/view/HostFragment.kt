package com.skifer.epam_internship_android_checkunov.presentation.feature.host.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.skifer.epam_internship_android_checkunov.R


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
        findNavController().navigate(
            HostFragmentDirections.actionHostFragmentToMealListFragment()
        )
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