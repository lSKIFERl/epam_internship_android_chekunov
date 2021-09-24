package com.skifer.epam_internship_android_checkunov.presentation.feature.settings.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.skifer.epam_internship_android_checkunov.domain.usecase.GetSortUseCase
import com.skifer.epam_internship_android_checkunov.domain.usecase.SetSortUseCase
import com.skifer.epam_internship_android_checkunov.presentation.feature.settings.viewmodel.SharedSettingsViewModel
import dagger.Module
import dagger.Provides

@Module
class SettingsModule {

    @Provides
    fun provideSharedSettingsViewModelFactory(
        setSortUseCase: SetSortUseCase,
        getSortUseCase: GetSortUseCase
    ): SharedSettingsViewModel = object : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            SharedSettingsViewModel(
                setSortUseCase,
                getSortUseCase
            )
            as T

    }
        .create(SharedSettingsViewModel::class.java)
}