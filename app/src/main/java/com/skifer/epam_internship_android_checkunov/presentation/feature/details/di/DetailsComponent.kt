package com.skifer.epam_internship_android_checkunov.presentation.feature.details.di

import com.skifer.epam_internship_android_checkunov.di.annotations.FragmentScope
import com.skifer.epam_internship_android_checkunov.getAppComponent
import com.skifer.epam_internship_android_checkunov.presentation.feature.details.view.MealDetailsFragment
import dagger.Subcomponent

@Subcomponent(
    modules = [DetailsModule::class]
)
interface DetailsComponent {

    @FragmentScope
    @Subcomponent.Factory
    interface Factory {
        fun create(): DetailsComponent
    }

    fun inject(mealDetailsFragment: MealDetailsFragment)

    companion object {

        lateinit var fragment: MealDetailsFragment

        fun create(fragment: MealDetailsFragment): DetailsComponent {
            this.fragment = fragment
            return fragment.requireContext().getAppComponent().createDetailsComponent().create()
        }
    }
}