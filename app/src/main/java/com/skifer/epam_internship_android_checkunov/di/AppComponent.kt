package com.skifer.epam_internship_android_checkunov.di

import android.content.Context
import com.skifer.epam_internship_android_checkunov.di.modules.*
import com.skifer.epam_internship_android_checkunov.domain.repository.CategoryRepository
import com.skifer.epam_internship_android_checkunov.domain.repository.MealListRepository
import com.skifer.epam_internship_android_checkunov.domain.repository.MealRepository
import com.skifer.epam_internship_android_checkunov.presentation.feature.settings.viewmodel.SharedSettingsViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        BindModule::class,
        DataBaseModule::class,
        NetworkModule::class,
        PrefsModule::class,
        SharedSettingsModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    abstract class Builder {

        @BindsInstance
        abstract fun bindContext(context: Context): Builder
        abstract fun build(): AppComponent
    }

    fun provideSharedSettings(): SharedSettingsViewModel

    fun provideMealListRepository(): MealListRepository

    fun provideMealModelRepository(): MealRepository

    fun provideTypeModelRepository(): CategoryRepository

}