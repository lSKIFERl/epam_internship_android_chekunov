package com.skifer.epam_internship_android_checkunov.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.skifer.epam_internship_android_checkunov.App
import com.skifer.epam_internship_android_checkunov.R

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private lateinit var ascSort: Button

    private lateinit var descSort: Button

    private lateinit var confirm: Button

    private val newSettings = mutableMapOf<String, String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        bind()
    }

    private fun initView() {
        view?.let {
            ascSort = it.findViewById(R.id.ASCSort)
            descSort = it.findViewById(R.id.DESCSort)
            confirm = it.findViewById(R.id.confirm)
            it.findViewById<Toolbar>(R.id.toolbar_settings).setNavigationOnClickListener {
                parentFragmentManager.popBackStack()
            }
        }
        ascSort.setOnClickListener {
            it.setBackgroundColor(resources.getColor(R.color.selected_type))
            descSort.setBackgroundColor(resources.getColor(R.color.slightly_gray))
            newSettings[SORT_MEALS_LIST] = SORT_ASC
        }
        descSort.setOnClickListener {
            it.setBackgroundColor(resources.getColor(R.color.selected_type))
            ascSort.setBackgroundColor(resources.getColor(R.color.slightly_gray))
            newSettings[SORT_MEALS_LIST] = SORT_DESC
        }
        confirm.setOnClickListener {
            App.instance.sharedPreferences
                .edit()
                .putString(
                    SORT_MEALS_LIST,
                    newSettings.get(
                        SORT_MEALS_LIST)
                        ?:App.instance.sharedPreferences.getString(
                            SORT_MEALS_LIST,
                            SORT_ASC
                        )
                )
                .apply()
            parentFragmentManager.popBackStack()
        }
    }

    private fun bind() {
        if (App.instance.sharedPreferences.getString(SORT_MEALS_LIST, SORT_ASC) == SORT_ASC) {
            ascSort.setBackgroundColor(resources.getColor(R.color.selected_type))
        } else {
            descSort.setBackgroundColor(resources.getColor(R.color.selected_type))
        }
    }

    companion object {
        private const val SORT_ASC = "SORT_ASC"
        private const val SORT_DESC = "SORT_DESC"
        const val SORT_MEALS_LIST = "SORT_MEALS_LIST"

        private const val PREVIOUS_SETTINGS = "PREVIOUS_SETTINGS"

        /**
         * Should be called instead instead of just instantiating the class
         */
        fun newInstance() = SettingsFragment().apply {
            //arguments = bundleOf(PREVIOUS_SETTINGS to ...) then get arguments somewhere
        }
    }

}