package com.skifer.epam_internship_android_checkunov.presentation.feature.types.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.skifer.epam_internship_android_checkunov.domain.usecase.TypeListUseCase

class TypeListFactory(
    private val useCase: TypeListUseCase
):ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        TypeListViewModel(useCase) as T
}