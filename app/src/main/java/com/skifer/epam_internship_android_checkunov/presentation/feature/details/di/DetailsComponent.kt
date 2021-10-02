package com.skifer.epam_internship_android_checkunov.presentation.feature.details.di

import com.skifer.epam_internship_android_checkunov.di.AppComponent
import com.skifer.epam_internship_android_checkunov.di.annotations.FragmentScope
import com.skifer.epam_internship_android_checkunov.presentation.feature.details.view.MealDetailsFragment
import dagger.Component

@FragmentScope
@Component(
    modules = [DetailsModule::class],
    dependencies = [AppComponent::class]
)
interface DetailsComponent {

    @Component.Factory
    interface Factory {
        fun create (appComponent: AppComponent): DetailsComponent
    }

    fun inject(mealDetailsFragment: MealDetailsFragment)

}