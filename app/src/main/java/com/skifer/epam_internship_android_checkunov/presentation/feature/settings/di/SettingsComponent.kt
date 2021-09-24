package com.skifer.epam_internship_android_checkunov.presentation.feature.settings.di

import com.skifer.epam_internship_android_checkunov.di.annotations.FragmentScope
import com.skifer.epam_internship_android_checkunov.getAppComponent
import com.skifer.epam_internship_android_checkunov.presentation.feature.settings.view.SettingsFragment
import dagger.Subcomponent

@Subcomponent(
    modules = [SettingsModule::class]
)
interface SettingsComponent {

    @FragmentScope
    @Subcomponent.Factory
    interface Factory {
        fun create(): SettingsComponent
    }

    fun inject(fragment: SettingsFragment)

    companion object {

        lateinit var fragment: SettingsFragment

        fun create(fragment: SettingsFragment): SettingsComponent {
            this.fragment = fragment
            return fragment.requireContext().getAppComponent().createSharedSettings().create()
        }
    }
}