package com.skifer.epam_internship_android_checkunov.presentation.feature.settings.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.skifer.epam_internship_android_checkunov.App
import com.skifer.epam_internship_android_checkunov.R
import com.skifer.epam_internship_android_checkunov.presentation.base.ShareViewModel


class SettingsFragment : BottomSheetDialogFragment() {

    private lateinit var ascSort: Button

    private lateinit var descSort: Button

    private lateinit var confirm: Button

    private val newSettings = mutableMapOf<String, String>()

    private var mBehavior: BottomSheetBehavior<View>? = null

    private val sorterSharedView: ShareViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        bind()
        mBehavior = BottomSheetBehavior.from(this.view?.parent as View)
        mBehavior?.state = BottomSheetBehavior.STATE_EXPANDED
    }

    private fun initView() {
        view?.let {
            ascSort = it.findViewById(R.id.ASCSort)
            descSort = it.findViewById(R.id.DESCSort)
            confirm = it.findViewById(R.id.confirm)
            it.findViewById<Toolbar>(R.id.toolbar_settings).setNavigationOnClickListener {
                dismiss()
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
                    newSettings[SORT_MEALS_LIST]
                        ?:App.instance.sharedPreferences.getString(
                            SORT_MEALS_LIST,
                            SORT_ASC
                        )
                )
                .apply()
            sorterSharedView.setSortOrder(
                newSettings[SORT_MEALS_LIST] ?: SORT_ASC
            )
            dismiss()
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

        /**
         * Should be called instead instead of just instantiating the class
         */
        fun newInstance() = SettingsFragment().apply {
            //arguments = bundleOf(PREVIOUS_SETTINGS to ...) then get arguments somewhere
        }
    }

}