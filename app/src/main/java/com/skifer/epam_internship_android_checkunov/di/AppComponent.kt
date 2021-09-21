package com.skifer.epam_internship_android_checkunov.di

import android.content.Context
import com.skifer.epam_internship_android_checkunov.di.modules.BindModule
import com.skifer.epam_internship_android_checkunov.di.modules.DataBaseModule
import com.skifer.epam_internship_android_checkunov.di.modules.NetworkModule
import com.skifer.epam_internship_android_checkunov.domain.repository.MealListRepository
import com.skifer.epam_internship_android_checkunov.domain.repository.MealModelRepository
import com.skifer.epam_internship_android_checkunov.domain.repository.TypeModelRepository
import com.skifer.epam_internship_android_checkunov.presentation.feature.details.di.DetailsComponent
import com.skifer.epam_internship_android_checkunov.presentation.feature.meals.di.MealsComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component (modules = [
    BindModule::class,
    DataBaseModule::class,
    NetworkModule::class,
])
interface AppComponent {

    @Component.Builder
    abstract class Builder {

        @BindsInstance
        abstract fun bindContext(context: Context): Builder
        abstract fun build(): AppComponent
    }

    fun createDetailsComponent(): DetailsComponent.Factory

    fun createMealsComponent(): MealsComponent.Factory

    fun provideMealListRepository(): MealListRepository

    fun provideMealModelRepository(): MealModelRepository

    fun provideTypeModelRepository(): TypeModelRepository

}