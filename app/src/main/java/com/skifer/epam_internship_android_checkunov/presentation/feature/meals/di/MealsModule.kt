package com.skifer.epam_internship_android_checkunov.presentation.feature.meals.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.skifer.epam_internship_android_checkunov.domain.usecase.GetCategoryListUseCase
import com.skifer.epam_internship_android_checkunov.domain.usecase.GetLastCategoryIdUseCase
import com.skifer.epam_internship_android_checkunov.domain.usecase.GetMealListUseCase
import com.skifer.epam_internship_android_checkunov.domain.usecase.SetLastCategoryIdUseCase
import com.skifer.epam_internship_android_checkunov.presentation.feature.meals.viewmodel.MealListViewModel
import dagger.Module
import dagger.Provides

@Module
class MealsModule {

    @Provides
    fun provideMealListViewModelFactory(
        getMealListUseCase: GetMealListUseCase,
        getCategoryListUseCase: GetCategoryListUseCase,
        setLastCategoryIdUseCase: SetLastCategoryIdUseCase,
        getLastCategoryIdUseCase: GetLastCategoryIdUseCase
    ): MealListViewModel = object : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            MealListViewModel(
                getMealListUseCase,
                getCategoryListUseCase,
                setLastCategoryIdUseCase,
                getLastCategoryIdUseCase
            ) as T

    }
        .create(MealListViewModel::class.java)
}