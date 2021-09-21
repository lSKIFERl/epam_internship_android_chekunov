package com.skifer.epam_internship_android_checkunov.presentation.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skifer.epam_internship_android_checkunov.presentation.model.MealModelListItem

class ShareViewModel: ViewModel() {
    val sortBy = MutableLiveData<String>()

    fun sort(list: List<MealModelListItem>, order: String?) =
        if (order.equals(SORT_DESC)) {
            list.sortedByDescending { it.strMeal }
        } else {
            list.sortedBy { it.strMeal }
        }

    fun setSortOrder(order: String) {
        if(order.equals(SORT_ASC) or order.equals(SORT_DESC)) {
            sortBy.value = order
        }
    }

    companion object {
        val SORT_ASC = "SORT_ASC"
        val SORT_DESC = "SORT_DESC"
    }
}