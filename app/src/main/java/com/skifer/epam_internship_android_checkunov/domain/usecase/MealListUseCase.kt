package com.skifer.epam_internship_android_checkunov.domain.usecase

import com.skifer.epam_internship_android_checkunov.domain.repository.MealListRepository

class MealListUseCase(private val repository: MealListRepository) {
    operator fun invoke(category: String) = repository.loadDishList(category)
}