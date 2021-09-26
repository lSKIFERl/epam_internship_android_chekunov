package com.skifer.epam_internship_android_checkunov.data.preferences

import android.content.SharedPreferences
import android.util.Log
import javax.inject.Inject

const val TYPE = "TYPE"

class CategorySharedPreferencesSource @Inject constructor(private val prefs: SharedPreferences) {

    fun setLastCategoryName(categoryName: String) {
        Log.i("Net", "SAVED CATEGORY $categoryName")
        prefs.edit().putString(TYPE, categoryName).apply()
    }

    fun getLastCategoryName(): String {
        Log.i("Net", "FETCHED CATEGORY" + prefs.getString(TYPE, "") ?: "")
        return prefs.getString(TYPE, "") ?: ""
    }
}