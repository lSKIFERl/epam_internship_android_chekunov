package com.skifer.epam_internship_android_checkunov.presentation.feature.meals.di

import com.skifer.epam_internship_android_checkunov.di.AppComponent
import com.skifer.epam_internship_android_checkunov.di.annotations.FragmentScope
import com.skifer.epam_internship_android_checkunov.presentation.feature.meals.view.MealListFragment
import dagger.Component

@FragmentScope
@Component(
    modules = [MealsModule::class],
    dependencies = [AppComponent::class]
)
interface MealsComponent {

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): MealsComponent
    }

    fun inject(mealListFragment: MealListFragment)
}