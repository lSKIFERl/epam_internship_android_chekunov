package com.skifer.epam_internship_android_checkunov.presentation.feature.settings.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skifer.epam_internship_android_checkunov.data.preferences.Sort
import com.skifer.epam_internship_android_checkunov.domain.usecase.GetSortUseCase
import com.skifer.epam_internship_android_checkunov.domain.usecase.SetSortUseCase
import com.skifer.epam_internship_android_checkunov.presentation.model.MealListItemModel

class SharedSettingsViewModel (
    private val setSort: SetSortUseCase,
    private val getSort: GetSortUseCase
): ViewModel() {

    val sortBy = MutableLiveData<Sort>()

    fun sort(listModel: List<MealListItemModel>) =
        if (getSort() == Sort.SORT_DESC) {
            listModel.sortedByDescending { it.strMeal }
        } else {
            listModel.sortedBy { it.strMeal }
        }

    fun setSortOrder(order: Sort) {
        setSort(order)
    }

    fun getSortOrder() = getSort()

    fun apply() {
        sortBy.value = getSort()
    }
}