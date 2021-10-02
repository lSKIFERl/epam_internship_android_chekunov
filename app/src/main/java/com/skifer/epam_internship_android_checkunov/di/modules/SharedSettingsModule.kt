package com.skifer.epam_internship_android_checkunov.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.skifer.epam_internship_android_checkunov.domain.usecase.GetSortUseCase
import com.skifer.epam_internship_android_checkunov.presentation.feature.settings.viewmodel.SharedSettingsViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SharedSettingsModule {

    @Singleton
    @Provides
    fun provideSharedSettingsViewModelFactory(getSortUseCase: GetSortUseCase)
            : SharedSettingsViewModel = object : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            SharedSettingsViewModel(getSortUseCase) as T

    }
        .create(SharedSettingsViewModel::class.java)
}