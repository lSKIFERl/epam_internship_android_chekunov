package com.skifer.epam_internship_android_checkunov.presentation.feature.meals.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.skifer.epam_internship_android_checkunov.domain.usecase.MealListUseCase

class MealListFactory(
    private val useCase: MealListUseCase,
    private val category: String
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        MealListViewModel(useCase, category) as T
}