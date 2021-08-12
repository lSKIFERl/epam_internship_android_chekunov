package com.skifer.epam_internship_android_checkunov.fragments.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.skifer.epam_internship_android_checkunov.net.repository.TypeModelRepository

class TypeListFactory(
    private val repository: TypeModelRepository
):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        TypeListViewModel(repository) as T
}