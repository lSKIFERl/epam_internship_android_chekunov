package com.skifer.epam_internship_android_checkunov.presentation.feature.details.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.skifer.epam_internship_android_checkunov.domain.usecase.GetMealUseCase
import com.skifer.epam_internship_android_checkunov.presentation.feature.details.viewmodel.MealDetailsViewModel
import dagger.Module
import dagger.Provides

@Module
class DetailsModule {
    @Provides
    fun provideMealModelViewModelFactory(
        getMealUseCase: GetMealUseCase
    ): MealDetailsViewModel = object : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            MealDetailsViewModel(getMealUseCase) as T

    }
        .create(MealDetailsViewModel::class.java)
}