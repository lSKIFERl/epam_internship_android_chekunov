package com.skifer.epam_internship_android_checkunov.presentation.feature.settings.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skifer.epam_internship_android_checkunov.data.preferences.Sort
import com.skifer.epam_internship_android_checkunov.domain.usecase.GetSortUseCase
import com.skifer.epam_internship_android_checkunov.presentation.model.MealListItemModel

class SharedSettingsViewModel(
    private val getSort: GetSortUseCase
): ViewModel() {

    private val mutableSort = MutableLiveData<Sort>()

    val sortBy: LiveData<Sort>
        get() = mutableSort

    init {
        getOrder()
    }

    private fun getOrder() {
        mutableSort.value = getSort()
    }

    fun sort(listModel: List<MealListItemModel>?) =
        if (mutableSort.value == Sort.SORT_DESC) {
            listModel?.sortedByDescending { it.strMeal }
        } else {
            listModel?.sortedBy { it.strMeal }
        }

    fun setSort(sort: Sort) {
        mutableSort.value = sort
    }
}