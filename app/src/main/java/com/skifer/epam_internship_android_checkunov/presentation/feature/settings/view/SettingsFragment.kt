package com.skifer.epam_internship_android_checkunov.presentation.feature.settings.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.skifer.epam_internship_android_checkunov.R
import com.skifer.epam_internship_android_checkunov.data.preferences.Sort
import com.skifer.epam_internship_android_checkunov.di.ComponentProvider
import com.skifer.epam_internship_android_checkunov.presentation.feature.settings.di.SettingsComponent
import com.skifer.epam_internship_android_checkunov.presentation.feature.settings.viewmodel.SharedSettingsViewModel
import javax.inject.Inject


class SettingsFragment : BottomSheetDialogFragment(), ComponentProvider<SettingsComponent> {

    private lateinit var ascSort: Button

    private lateinit var descSort: Button

    private lateinit var confirm: Button

    private var mBehavior: BottomSheetBehavior<View>? = null

    @Inject
    lateinit var sorterSharedView: SharedSettingsViewModel

    override val component: SettingsComponent by lazy {
        SettingsComponent.create(this)
    }

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
        component.inject(this)
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
            sorterSharedView.setSortOrder(Sort.SORT_ASC)
        }
        descSort.setOnClickListener {
            it.setBackgroundColor(resources.getColor(R.color.selected_type))
            ascSort.setBackgroundColor(resources.getColor(R.color.slightly_gray))
            sorterSharedView.setSortOrder(Sort.SORT_DESC)
        }
        confirm.setOnClickListener {
            sorterSharedView.apply()
            dismiss()
        }
    }

    private fun bind() {
        if (sorterSharedView.lastOrder == Sort.SORT_ASC) {
            ascSort.setBackgroundColor(resources.getColor(R.color.selected_type))
        } else {
            descSort.setBackgroundColor(resources.getColor(R.color.selected_type))
        }
    }

    companion object {

        /**
         * Should be called instead instead of just instantiating the class
         */
        fun newInstance() = SettingsFragment().apply {
            //arguments = bundleOf(PREVIOUS_SETTINGS to ...) then get arguments somewhere
        }
    }

}