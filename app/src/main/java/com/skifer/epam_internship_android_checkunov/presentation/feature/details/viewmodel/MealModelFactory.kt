package com.skifer.epam_internship_android_checkunov.presentation.feature.details.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.skifer.epam_internship_android_checkunov.domain.usecase.MealModelUseCase

class MealModelFactory(
    private val useCase: MealModelUseCase,
    private val id: Int
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        MealModelViewModel(useCase, id) as T
}