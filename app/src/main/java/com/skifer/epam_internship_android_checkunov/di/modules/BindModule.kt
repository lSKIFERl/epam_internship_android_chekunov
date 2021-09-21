package com.skifer.epam_internship_android_checkunov.di.modules

import com.skifer.epam_internship_android_checkunov.data.repository.MealListRepositoryImpl
import com.skifer.epam_internship_android_checkunov.data.repository.MealModelRepositoryImpl
import com.skifer.epam_internship_android_checkunov.data.repository.TypeModelRepositoryImpl
import com.skifer.epam_internship_android_checkunov.domain.repository.MealListRepository
import com.skifer.epam_internship_android_checkunov.domain.repository.MealModelRepository
import com.skifer.epam_internship_android_checkunov.domain.repository.TypeModelRepository
import dagger.Binds
import dagger.Module

@Module
interface BindModule {

    @Binds
    fun provideMealListRepository(
        repository: MealListRepositoryImpl
    ): MealListRepository

    @Binds
    fun provideMealModelRepository(
        repository: MealModelRepositoryImpl
    ): MealModelRepository

    @Binds
    fun provideTypeModelRepository(
        repository: TypeModelRepositoryImpl
    ): TypeModelRepository
}