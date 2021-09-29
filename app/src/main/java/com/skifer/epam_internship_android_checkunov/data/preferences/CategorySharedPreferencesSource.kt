package com.skifer.epam_internship_android_checkunov.data.preferences

import android.content.SharedPreferences
import android.util.Log
import javax.inject.Inject

const val CATEGORY = "CATEGORY"
const val LAST_CATEGORY_ID = "LAST_CATEGORY_ID"

class CategorySharedPreferencesSource @Inject constructor(private val prefs: SharedPreferences) {

    fun setLastCategoryName(categoryName: String) {
        Log.i("Net", "SAVED CATEGORY $categoryName")
        prefs.edit().putString(CATEGORY, categoryName).apply()
    }

    fun getLastCategoryName(): String {
        Log.i("Net", "FETCHED CATEGORY" + prefs.getString(CATEGORY, "") ?: "")
        return prefs.getString(CATEGORY, "") ?: ""
    }

    fun setLastCategoryId(id: Long) {
        prefs.edit().putLong(LAST_CATEGORY_ID, id).apply()
    }

    fun getLastCategoryId(): Long {
        return prefs.getLong(LAST_CATEGORY_ID, 0)
    }
}