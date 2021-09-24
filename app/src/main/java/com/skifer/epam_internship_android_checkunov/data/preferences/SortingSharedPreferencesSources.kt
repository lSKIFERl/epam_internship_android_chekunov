package com.skifer.epam_internship_android_checkunov.data.preferences

import android.content.SharedPreferences
import javax.inject.Inject

const val SORT_MEALS_LIST = "SORT_MEALS_LIST"

enum class Sort {
    SORT_ASC,
    SORT_DESC
}

class SortingSharedPreferencesSources @Inject constructor(private val prefs: SharedPreferences){

    fun setSort(sort: Sort) =
        prefs.edit().putString(SORT_MEALS_LIST, sort.name).apply()

    fun getSort(): Sort =
        if (prefs.getString(SORT_MEALS_LIST, Sort.SORT_ASC.name) == Sort.SORT_ASC.name)
            Sort.SORT_ASC else Sort.SORT_DESC
}