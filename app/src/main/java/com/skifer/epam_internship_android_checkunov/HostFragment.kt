package com.skifer.epam_internship_android_checkunov

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment

/**
 * First and main App Fragment
 */
class HostFragment : Fragment(R.layout.fragment_host) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true);
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_host, menu)
        super.onCreateOptionsMenu(menu, inflater)
        return
    }

    /**
     * Starts [MealListFragment] immediately
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parentFragmentManager
                .beginTransaction()
                .replace(
                    R.id.type_list_container,
                    TypeListFragment
                        .newInstance()
                )
                .commit()
        parentFragmentManager
                .beginTransaction()
                .add(
                    R.id.meal_list_container,
                    MealListFragment
                        .newInstance()
                )
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