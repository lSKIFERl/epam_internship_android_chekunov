package com.skifer.epam_internship_android_checkunov.presentation.feature.meals.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.skifer.epam_internship_android_checkunov.domain.usecase.MealListUseCase
import com.skifer.epam_internship_android_checkunov.domain.usecase.TypeListUseCase
import com.skifer.epam_internship_android_checkunov.presentation.feature.meals.viewmodel.MealListViewModel
import dagger.Module
import dagger.Provides

@Module
class MealsModule {
    @Provides
    fun provideMealListViewModelFactory(
        mealListUseCase: MealListUseCase,
        typeListUseCase: TypeListUseCase
    ): MealListViewModel = object : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            MealListViewModel(mealListUseCase, typeListUseCase) as T

    }
        .create(MealListViewModel::class.java)
}