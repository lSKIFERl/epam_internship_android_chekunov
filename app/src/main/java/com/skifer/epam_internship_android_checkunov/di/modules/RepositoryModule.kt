package com.skifer.epam_internship_android_checkunov.di.modules

import com.skifer.epam_internship_android_checkunov.data.database.database.ModelsDataBase
import com.skifer.epam_internship_android_checkunov.data.network.DishApi
import com.skifer.epam_internship_android_checkunov.data.repository.MealListRepositoryImpl
import com.skifer.epam_internship_android_checkunov.data.repository.MealModelRepositoryImpl
import com.skifer.epam_internship_android_checkunov.data.repository.TypeModelRepositoryImpl
import com.skifer.epam_internship_android_checkunov.domain.repository.MealListRepository
import com.skifer.epam_internship_android_checkunov.domain.repository.MealModelRepository
import com.skifer.epam_internship_android_checkunov.domain.repository.TypeModelRepository
import dagger.Module
import dagger.Provides

@Module(includes = [NetworkModule::class, DataBaseModule::class])
class RepositoryModule {

    @Provides
    fun provideMealListRepository(
        dishApi: DishApi
    ): MealListRepository =
        MealListRepositoryImpl(dishApi)

    @Provides
    fun provideMealModelRepository(
        dishApi: DishApi
    ): MealModelRepository =
        MealModelRepositoryImpl(dishApi)

    @Provides
    fun provideTypeModelRepository(
        dishApi: DishApi,
        modelsDataBase: ModelsDataBase
    ): TypeModelRepository =
        TypeModelRepositoryImpl(dishApi, modelsDataBase)
}