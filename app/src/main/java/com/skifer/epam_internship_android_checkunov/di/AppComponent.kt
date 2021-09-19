package com.skifer.epam_internship_android_checkunov.di

import android.content.Context
import com.skifer.epam_internship_android_checkunov.di.modules.UseCaseModule
import com.skifer.epam_internship_android_checkunov.domain.usecase.MealListUseCase
import com.skifer.epam_internship_android_checkunov.domain.usecase.MealModelUseCase
import com.skifer.epam_internship_android_checkunov.domain.usecase.TypeListUseCase
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [UseCaseModule::class])
interface AppComponent {

    val mealListUseCase: MealListUseCase

    val mealModelUseCase: MealModelUseCase

    val typeListUseCase: TypeListUseCase

    @Component.Builder
    abstract class Builder {

        @BindsInstance
        abstract fun bindContext(context: Context): Builder
        abstract fun build(): AppComponent
    }

}