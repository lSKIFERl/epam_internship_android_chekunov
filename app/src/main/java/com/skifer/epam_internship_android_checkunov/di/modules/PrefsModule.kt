package com.skifer.epam_internship_android_checkunov.di.modules

import android.content.Context
import com.skifer.epam_internship_android_checkunov.data.preferences.CategorySharedPreferencesSource
import com.skifer.epam_internship_android_checkunov.data.preferences.SortingSharedPreferencesSources
import dagger.Module
import dagger.Provides
import android.content.SharedPreferences as Preferences

@Module
class PrefsModule {
    @Provides
    fun provideSharedPrefs(context: Context): Preferences =
        context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    @Provides
    fun provideCategoryPrefsSource(prefs: Preferences) = CategorySharedPreferencesSource(prefs)

    @Provides
    fun provideSortingPrefsSource(prefs: Preferences) = SortingSharedPreferencesSources(prefs)
}