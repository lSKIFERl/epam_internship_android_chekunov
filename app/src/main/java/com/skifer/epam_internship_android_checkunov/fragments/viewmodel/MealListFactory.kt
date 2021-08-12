package com.skifer.epam_internship_android_checkunov.fragments.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.skifer.epam_internship_android_checkunov.net.repository.MealListRepository

class MealListFactory(
    private val repository: MealListRepository,
    private val category: String
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        MealListViewModel(repository, category) as T
}