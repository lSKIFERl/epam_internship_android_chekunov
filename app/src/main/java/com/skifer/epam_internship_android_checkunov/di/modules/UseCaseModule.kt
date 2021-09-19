package com.skifer.epam_internship_android_checkunov.di.modules

import com.skifer.epam_internship_android_checkunov.domain.repository.MealListRepository
import com.skifer.epam_internship_android_checkunov.domain.repository.MealModelRepository
import com.skifer.epam_internship_android_checkunov.domain.repository.TypeModelRepository
import com.skifer.epam_internship_android_checkunov.domain.usecase.MealListUseCase
import com.skifer.epam_internship_android_checkunov.domain.usecase.MealModelUseCase
import com.skifer.epam_internship_android_checkunov.domain.usecase.TypeListUseCase
import dagger.Module
import dagger.Provides

@Module(includes = [RepositoryModule::class])
class UseCaseModule {

    @Provides
    fun provideMealListUseCase(
        repository: MealListRepository
    ) = MealListUseCase(repository)

    @Provides
    fun provideMealModelUseCase(
        repository: MealModelRepository
    ) = MealModelUseCase(repository)

    @Provides
    fun provideTypeModelUseCase(
        repository: TypeModelRepository
    ) = TypeListUseCase(repository)
}