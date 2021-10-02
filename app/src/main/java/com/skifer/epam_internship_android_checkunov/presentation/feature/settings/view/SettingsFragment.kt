package com.skifer.epam_internship_android_checkunov.presentation.feature.settings.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.skifer.epam_internship_android_checkunov.App
import com.skifer.epam_internship_android_checkunov.R
import com.skifer.epam_internship_android_checkunov.data.preferences.Sort
import com.skifer.epam_internship_android_checkunov.di.ComponentProvider
import com.skifer.epam_internship_android_checkunov.presentation.feature.settings.di.DaggerSettingsComponent
import com.skifer.epam_internship_android_checkunov.presentation.feature.settings.di.SettingsComponent
import com.skifer.epam_internship_android_checkunov.presentation.feature.settings.viewmodel.SettingsViewModel
import com.skifer.epam_internship_android_checkunov.presentation.feature.settings.viewmodel.SharedSettingsViewModel
import javax.inject.Inject


class SettingsFragment : BottomSheetDialogFragment(), ComponentProvider<SettingsComponent> {

    @Inject
    lateinit var sorterSharedView: SharedSettingsViewModel

    @Inject
    lateinit var viewModel: SettingsViewModel

    override val component: SettingsComponent by lazy {
        DaggerSettingsComponent.factory().create(App.instance.appComponent)
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
        component.inject(this)
        initView()
        val mBehavior = BottomSheetBehavior.from(this.view?.parent as View)
        mBehavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    private fun initView() {

        var ascSort: Button? = null

        var descSort: Button? = null

        var order = Sort.SORT_ASC

        view?.let {
            ascSort = it.findViewById(R.id.ASCSort)
            descSort = it.findViewById(R.id.DESCSort)
            it.findViewById<Button>(R.id.confirm).setOnClickListener {
                viewModel.apply()
                close(order)
            }
            it.findViewById<Toolbar>(R.id.toolbar_settings).setNavigationOnClickListener {
                dismiss()
            }
        }

        viewModel.sortBy.observe(viewLifecycleOwner) {
            order = it
            if (order == Sort.SORT_ASC)
                toggleColor(ascSort, descSort) else toggleColor(descSort, ascSort)
        }

        ascSort?.setOnClickListener {
            toggleColor(it, descSort)
            viewModel.setSortOrder(Sort.SORT_ASC)
        }

        descSort?.setOnClickListener {
            toggleColor(it, ascSort)
            viewModel.setSortOrder(Sort.SORT_DESC)
        }
    }

    private fun toggleColor(pressedButton: View?, unPressedButton: View?) {
        pressedButton?.setBackgroundColor(resources.getColor(R.color.selected_type))
        unPressedButton?.setBackgroundColor(resources.getColor(R.color.slightly_gray))
    }

    private fun close(sort: Sort) {
        sorterSharedView.setSort(sort)
        dismiss()
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