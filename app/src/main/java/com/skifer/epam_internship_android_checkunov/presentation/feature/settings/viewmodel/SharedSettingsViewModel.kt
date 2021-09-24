package com.skifer.epam_internship_android_checkunov.presentation.feature.settings.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skifer.epam_internship_android_checkunov.data.preferences.Sort
import com.skifer.epam_internship_android_checkunov.domain.usecase.GetSortUseCase
import com.skifer.epam_internship_android_checkunov.domain.usecase.SetSortUseCase
import com.skifer.epam_internship_android_checkunov.presentation.model.MealModelListItem

class SharedSettingsViewModel (
    private val setSort: SetSortUseCase,
    private val getSort: GetSortUseCase
): ViewModel() {

    val sortBy = MutableLiveData<Sort>()

    fun sort(list: List<MealModelListItem>) =
        if (getSort() == Sort.SORT_DESC) {
            list.sortedByDescending { it.strMeal }
        } else {
            list.sortedBy { it.strMeal }
        }

    fun setSortOrder(order: Sort) {
        setSort(order)
    }

    fun getSortOrder() = getSort()

    fun apply() {
        sortBy.value = getSort()
    }
}