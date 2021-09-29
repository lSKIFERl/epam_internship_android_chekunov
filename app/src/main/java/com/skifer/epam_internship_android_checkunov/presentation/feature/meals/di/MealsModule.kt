package com.skifer.epam_internship_android_checkunov.presentation.feature.meals.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.skifer.epam_internship_android_checkunov.domain.usecase.*
import com.skifer.epam_internship_android_checkunov.presentation.feature.meals.viewmodel.MealListViewModel
import dagger.Module
import dagger.Provides

@Module
class MealsModule {

    @Provides
    fun provideMealListViewModelFactory(
        getMealListUseCase: GetMealListUseCase,
        getCategoryListUseCase: GetCategoryListUseCase,
        setLastCategoryUseCase: SetLastCategoryUseCase,
        getLastCategoryUseCase: GetLastCategoryUseCase,
        setLastCategoryIdUseCase: SetLastCategoryIdUseCase,
        getLastCategoryIdUseCase: GetLastCategoryIdUseCase
    ): MealListViewModel = object : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            MealListViewModel(
                getMealListUseCase,
                getCategoryListUseCase,
                setLastCategoryUseCase,
                getLastCategoryUseCase,
                setLastCategoryIdUseCase,
                getLastCategoryIdUseCase
            ) as T

    }
        .create(MealListViewModel::class.java)
}