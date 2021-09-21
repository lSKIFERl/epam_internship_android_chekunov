package com.skifer.epam_internship_android_checkunov.presentation.feature.details.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.skifer.epam_internship_android_checkunov.domain.usecase.MealModelUseCase
import com.skifer.epam_internship_android_checkunov.presentation.feature.details.viewmodel.MealModelViewModel
import dagger.Module
import dagger.Provides

@Module
class DetailsModule {
    @Provides
    fun provideMealModelViewModelFactory(
        useCase: MealModelUseCase
    ): MealModelViewModel = object : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            MealModelViewModel(useCase) as T

    }
        .create(MealModelViewModel::class.java)
}