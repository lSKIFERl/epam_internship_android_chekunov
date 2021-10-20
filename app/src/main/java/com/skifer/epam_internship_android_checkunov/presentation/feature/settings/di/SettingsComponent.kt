package com.skifer.epam_internship_android_checkunov.presentation.feature.settings.di

import com.skifer.epam_internship_android_checkunov.di.AppComponent
import com.skifer.epam_internship_android_checkunov.di.annotations.FragmentScope
import com.skifer.epam_internship_android_checkunov.presentation.feature.settings.view.SettingsFragment
import dagger.Component

@FragmentScope
@Component(
    modules = [SettingsModule::class],
    dependencies = [AppComponent::class]
)
interface SettingsComponent {

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): SettingsComponent
    }

    fun inject(fragment: SettingsFragment)
}