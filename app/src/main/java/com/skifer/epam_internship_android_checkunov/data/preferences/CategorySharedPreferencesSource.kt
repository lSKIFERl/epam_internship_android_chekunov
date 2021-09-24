package com.skifer.epam_internship_android_checkunov.data.preferences

import android.content.SharedPreferences
import javax.inject.Inject

const val TYPE = "TYPE"

class CategorySharedPreferencesSource @Inject constructor(private val prefs: SharedPreferences) {

    fun setLastCategoryName(categoryName: String) =
        prefs.edit().putString(TYPE, categoryName).apply()

    fun getLastCategoryName(): String =
        prefs.getString(TYPE, "") ?: ""
}