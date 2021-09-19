package com.skifer.epam_internship_android_checkunov.presentation.feature.meals.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.skifer.epam_internship_android_checkunov.domain.usecase.MealListUseCase
import com.skifer.epam_internship_android_checkunov.domain.usecase.TypeListUseCase

class MealListFactory(
    private val mealListUseCase: MealListUseCase,
    private val typeListUseCase: TypeListUseCase,
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        MealListViewModel(mealListUseCase, typeListUseCase) as T
}