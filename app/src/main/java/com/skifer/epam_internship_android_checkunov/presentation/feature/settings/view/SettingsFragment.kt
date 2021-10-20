package com.skifer.epam_internship_android_checkunov.presentation.feature.settings.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.skifer.epam_internship_android_checkunov.App
import com.skifer.epam_internship_android_checkunov.R
import com.skifer.epam_internship_android_checkunov.databinding.FragmentSettingsBinding
import com.skifer.epam_internship_android_checkunov.di.ComponentProvider
import com.skifer.epam_internship_android_checkunov.domain.entity.Sort
import com.skifer.epam_internship_android_checkunov.presentation.feature.settings.di.DaggerSettingsComponent
import com.skifer.epam_internship_android_checkunov.presentation.feature.settings.di.SettingsComponent
import com.skifer.epam_internship_android_checkunov.presentation.feature.settings.viewmodel.SettingsViewModel
import com.skifer.epam_internship_android_checkunov.presentation.feature.settings.viewmodel.SharedSettingsViewModel
import javax.inject.Inject


class SettingsFragment : BottomSheetDialogFragment(), ComponentProvider<SettingsComponent> {

    private lateinit var binding: FragmentSettingsBinding

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
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        component.inject(this)

        observeLiveData()

        initView()

        val mBehavior = BottomSheetBehavior.from(this.view?.parent as View)
        mBehavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    private fun initView() =
        binding.apply {
            toolbarSettings.setNavigationOnClickListener {
                dismiss()
            }

            ascSort.setOnClickListener {
                viewModel.setSortOrder(Sort.SORT_ASC)
            }

            descSort.setOnClickListener {
                viewModel.setSortOrder(Sort.SORT_DESC)
            }

            apply.setOnClickListener {
                sorterSharedView.apply()
                dismiss()
            }
        }

    private fun observeLiveData() =
        viewModel.sortByData.observe(viewLifecycleOwner) {
            toggleColor(viewModel.isSortAsc())
            sorterSharedView.setSort(it)
        }

    private fun toggleColor(isOrderAsc: Boolean) =
        if (isOrderAsc) {
            binding.ascSort.setBackgroundColor(resources.getColor(R.color.selected_type))
            binding.descSort.setBackgroundColor(resources.getColor(R.color.slightly_gray))
        } else {
            binding.ascSort.setBackgroundColor(resources.getColor(R.color.slightly_gray))
            binding.descSort.setBackgroundColor(resources.getColor(R.color.selected_type))
        }

    companion object {

        /**
         * Should be called instead of just instantiating the class
         */
        fun newInstance() = SettingsFragment().apply {
            //arguments = bundleOf(PREVIOUS_SETTINGS to ...) then get arguments somewhere
        }
    }

}