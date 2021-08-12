package com.skifer.epam_internship_android_checkunov.fragments.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.skifer.epam_internship_android_checkunov.net.repository.MealModelRepository

class MealModelFactory(
    private val repository: MealModelRepository,
    private val id: Int
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        MealModelViewModel(repository, id) as T
}