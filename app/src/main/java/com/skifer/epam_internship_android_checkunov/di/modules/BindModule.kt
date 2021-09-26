package com.skifer.epam_internship_android_checkunov.di.modules

import com.skifer.epam_internship_android_checkunov.data.repository.CategoryRepositoryImpl
import com.skifer.epam_internship_android_checkunov.data.repository.MealListRepositoryImpl
import com.skifer.epam_internship_android_checkunov.data.repository.MealRepositoryImpl
import com.skifer.epam_internship_android_checkunov.domain.repository.MealListRepository
import com.skifer.epam_internship_android_checkunov.domain.repository.MealRepository
import com.skifer.epam_internship_android_checkunov.domain.repository.CategoryRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface BindModule {

    @Binds
    @Singleton
    fun provideMealListRepository(
        repository: MealListRepositoryImpl
    ): MealListRepository

    @Binds
    @Singleton
    fun provideMealRepository(
        repository: MealRepositoryImpl
    ): MealRepository

    @Binds
    @Singleton
    fun provideTypeRepository(
        repository: CategoryRepositoryImpl
    ): CategoryRepository
}