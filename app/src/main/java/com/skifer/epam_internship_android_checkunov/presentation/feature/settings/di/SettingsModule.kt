package com.skifer.epam_internship_android_checkunov.presentation.feature.settings.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.skifer.epam_internship_android_checkunov.domain.usecase.GetSortUseCase
import com.skifer.epam_internship_android_checkunov.domain.usecase.SetSortUseCase
import com.skifer.epam_internship_android_checkunov.presentation.feature.settings.viewmodel.SettingsViewModel
import dagger.Module
import dagger.Provides

@Module
class SettingsModule {

    @Provides
    fun provideSettingsViewModelFactory(
        setSortUseCase: SetSortUseCase,
        getSortUseCase: GetSortUseCase
    ): SettingsViewModel = object : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            SettingsViewModel(
                setSortUseCase,
                getSortUseCase
            )
            as T

    }
        .create(SettingsViewModel::class.java)
}