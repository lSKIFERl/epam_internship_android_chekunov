package com.skifer.epam_internship_android_checkunov

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

/**
 * First and main App Fragment
 */
class HostFragment : Fragment(R.layout.fragment_host) {

    /**
     * Starts [MealListFragment] immediately
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parentFragmentManager
            .beginTransaction()
            .replace(
                    R.id.containerHost,
                    MealListFragment.
                    newInstance())
            .commit()
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