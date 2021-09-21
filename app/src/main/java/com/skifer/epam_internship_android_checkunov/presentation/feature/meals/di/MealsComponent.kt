package com.skifer.epam_internship_android_checkunov.presentation.feature.meals.di

import com.skifer.epam_internship_android_checkunov.di.annotations.FragmentScope
import com.skifer.epam_internship_android_checkunov.getAppComponent
import com.skifer.epam_internship_android_checkunov.presentation.feature.meals.view.MealListFragment
import dagger.Subcomponent

@Subcomponent(
    modules = [MealsModule::class]
)
interface MealsComponent {

    @FragmentScope
    @Subcomponent.Factory
    interface Factory {
        fun create(): MealsComponent
    }

    fun inject(mealListFragment: MealListFragment)

    companion object {

        lateinit var fragment: MealListFragment

        fun create(fragment: MealListFragment): MealsComponent {
            this.fragment = fragment
            return fragment.requireContext().getAppComponent().createMealsComponent().create()
        }
    }
}