package com.skifer.epam_internship_android_checkunov.data.preferences

import android.content.SharedPreferences
import javax.inject.Inject

class CategorySharedPreferencesSource @Inject constructor(private val prefs: SharedPreferences) {

    fun setLastCategoryId(id: Long) {
        prefs.edit().putLong(LAST_CATEGORY_ID, id).apply()
    }

    fun getLastCategoryId(): Long {
        return prefs.getLong(LAST_CATEGORY_ID, DEFAULT_ID)
    }

    companion object {
        const val LAST_CATEGORY_ID = "LAST_CATEGORY_ID"
        private const val DEFAULT_ID = 0L
    }
}